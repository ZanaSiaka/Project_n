import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/EnregistrerVendeurServlet")
public class EnregistrerVendeurServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
    private static final String DB_USER = "votre_utilisateur";
    private static final String DB_PASSWORD = "votre_mot_de_passe";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String numVendeur = request.getParameter("numVendeur");
        String nomVendeur = request.getParameter("nomVendeur");
        String prenomVendeur = request.getParameter("prenomVendeur");
        String adresseVendeur = request.getParameter("adresseVendeur");
        String mailVendeur = request.getParameter("mailVendeur");
        String telVendeur = request.getParameter("telVendeur");
        String mdpVendeur = request.getParameter("mdpVendeur");

        if (enregistrerVendeur(numVendeur, nomVendeur, prenomVendeur, adresseVendeur, mailVendeur, telVendeur, mdpVendeur)) {
            // L'enregistrement du vendeur a réussi
            response.sendRedirect("vendeurEnregistre.jsp");
        } else {
            // L'enregistrement du vendeur a échoué
            response.sendRedirect("echecEnregistrementVendeur.jsp");
        }
    }

    private boolean enregistrerVendeur(String numVendeur, String nomVendeur, String prenomVendeur, String adresseVendeur, String mailVendeur, String telVendeur, String mdpVendeur) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO HR.VENDEUR (NUMVENDEUR, NOMVENDEUR, PRENVENDEUR, ADRESSEVENDEUR, MAILVENDEUR, TELVENDEUR, MDPVENDEUR) VALUES (SEQ.nextval, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, numVendeur);
                    preparedStatement.setString(2, nomVendeur);
                    preparedStatement.setString(3, prenomVendeur);
                    preparedStatement.setString(4, adresseVendeur);
                    preparedStatement.setString(5, mailVendeur);
                    preparedStatement.setString(6, telVendeur);
                    preparedStatement.setString(7, mdpVendeur);

                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Ajoutez une gestion d'erreur appropriée en production
            return false;
        }
    }
}
