package com.example.project_n;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


import org.mindrot.jbcrypt.BCrypt; // Import de la classe BCrypt

@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("MailClient");
        String password = request.getParameter("MotDePasse");

        // Informations pour la connexion à la base de données (exemple)
        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
        String dbUser = "hr";
        String dbPassword = "hr";

        try {
            // Établir la connexion à la base de données
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Préparer la requête pour récupérer le mot de passe haché en fonction de l'email
            String query = "SELECT \"MotDePasse\" FROM CLIENT WHERE \"MailClient\" = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            // Exécuter la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Si l'email existe, récupérer le mot de passe haché depuis la base de données
                String hashedPasswordFromDB = ((ResultSet) resultSet).getString("MotDePasse");

                // Vérification du mot de passe avec BCrypt
                if (BCrypt.checkpw(password, hashedPasswordFromDB)) {
                    // Authentification réussie
                    // Vous pouvez rediriger vers une page appropriée pour un accès réussi
                    response.sendRedirect("index.jsp");
                } else {
                    // Authentification échouée - Mot de passe incorrect
                    response.sendRedirect("connexion.jsp");
                }
            } else {
                // Authentification échouée - Email non trouvé
                response.sendRedirect("connexion.jsp");
            }

            // Fermer les connexions et les ressources
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions appropriées
        }
    }

}
