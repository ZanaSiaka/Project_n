package com.example.project_n;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AjouterArticleServlet")
@MultipartConfig
public class AjouterArticleServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "hr";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomArticle = request.getParameter("NomArticle");
        String qteArticleStr = request.getParameter("QteArticle");
        String prixStr = request.getParameter("Prix");
        String descArticle = request.getParameter("DescArticle");

        try {
                int qteArticle = Integer.parseInt(qteArticleStr);
                double prix = Double.parseDouble(prixStr);

                // Récupérez le fichier image depuis la r equête
                Part filePart = request.getPart("ImgArticle");
                String fileName = filePart.getSubmittedFileName();


                if (filePart != null) {
                    // Spécifier le chemin où le fichier doit être sauvegardé sur le disque
                    String savePath = getServletContext().getRealPath("/photoProduit/") + File.separator + fileName;

                    // Vérifiez si le répertoire existe, sinon le crée
                    File fileSaveDir = new File(savePath);
                    if (!fileSaveDir.getParentFile().exists()) {
                        fileSaveDir.getParentFile().mkdirs();
                    }

                    // Utiliser un flux de sortie pour écrire le contenu de la partie sur le disque
                    try (InputStream input = filePart.getInputStream();
                         FileOutputStream output = new FileOutputStream(fileSaveDir)) {

                        byte[] buffer = new byte[10240];
                        int length;
                        while ((length = input.read(buffer)) > 0) {
                            output.write(buffer, 0, length);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Gérer l'exception, si nécessaire
                    }


                    if (ajouterArticle( nomArticle, qteArticle, prix, descArticle, fileName)) {
                        // L'article a été ajouté avec succès
                        request.setAttribute("successMessage", "L'article a été ajouté avec succès.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // L'ajout a échoué
                        request.setAttribute("errorMessage", "Produit non ajouté.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
                        dispatcher.forward(request, response);
                    }

            } else {
                // Gérez le cas où dateArriveeStr est null ou vide
                request.setAttribute("errorMessage", "Erreur numéro 1");
                RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException | SQLException e) {
            // Erreur lors de la conversion des données
            e.printStackTrace(); // Ajoutez une gestion d'erreur appropriée en production
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("inscription.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean ajouterArticle(String nomArticle, int qteArticle, double prix, String descArticle, String imgInputStream) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO ARTICLES (NUMARTICLE, NOMARTICLE, DATEARRIVEE, QTEARTICLE, PRIX, DESCARTICLE, IMGARTICLE) VALUES (SEQ.nextval, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, nomArticle);
                    preparedStatement.setInt(2, qteArticle);
                    preparedStatement.setDouble(3, prix);
                    preparedStatement.setString(4, descArticle);
                    preparedStatement.setString(5, imgInputStream);


                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Ajoutez une gestion d'erreur appropriée en production
            return false;
        }
    }

    // Méthode pour valider le type MIME de l'image (à personnaliser selon vos besoins)
    private boolean isValidImage(InputStream imgInputStream) throws IOException {
        // Ajoutez votre logique de validation ici
        // Par exemple, vous pouvez utiliser des bibliothèques comme Apache Tika pour vérifier le type MIME
        // Retournez true si l'image est valide, sinon false
        return true;
    }
}
