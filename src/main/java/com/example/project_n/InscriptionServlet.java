package com.example.project_n;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



@WebServlet(value = "/InscriptionServlet")
abstract
class InscriptionServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String nomClient = request.getParameter("NomClient");
        String prenClient = request.getParameter("PrenClient");
        String mailClient = request.getParameter("MailClient");
        String telClient = request.getParameter("TelClient");
        String motDePasse = request.getParameter("MotDePasse");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // Chargement du pilote JDBC Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // URL de connexion à la base de données Oracle
            String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xepdb1"; // Assurez-vous d'ajuster le port et le nom de la base de données HR

            // Établissement de la connexion
            connection = DriverManager.getConnection(jdbcUrl, "hr", "hr"); // Remplacez par votre nom d'utilisateur et mot de passe

            // Requête d'insertion pour insérer les données du client dans la table HR.CLIENT (exemple)
            String insertQuery = "INSERT INTO HR.CLIENT (\"NumClient\", \"NomClient\", \"PrenClient\", \"MailClient\", \"TelClient\", \"MotDePasse\") VALUES (HR.CLIENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

            // Création de la déclaration préparée
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nomClient);
            preparedStatement.setString(2, prenClient);
            preparedStatement.setString(3, mailClient);
            preparedStatement.setString (4, telClient);
            preparedStatement.setString(5, motDePasse);

            // Exécution de la requête d'insertion
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Redirection vers une page de confirmation en cas de succès
                response.sendRedirect("index.jsp");
            } else {
                // Gestion de l'échec de l'insertion
                response.sendRedirect("/inscription.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Gérer les exceptions appropriées
            // Redirection vers une page d'erreur en cas d'exception
            response.sendRedirect("/inscription.jsp");
        } finally {
            // Fermeture des ressources (connexion, déclaration préparée, etc.)
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions appropriées pour la fermeture des ressources
            }
        }
    }

    public void doPost(com.example.project_n.HttpServletRequest request, com.example.project_n.HttpServletResponse response, String NumClient, String NomClient, String PrenClient, String MailClient, int TelClient, String MotDePasse) throws com.example.project_n.ServletException, IOException {
    }
}
