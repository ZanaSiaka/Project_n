package com.example.project_n;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            // Charger le pilote JDBC Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Établir la connexion à la base de données Oracle
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xepdb1", "hr", "hr");

            // Écrire la requête SQL
            String query = "SELECT * FROM CLIENT WHERE MailClient=? AND MotDePasse=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Exécuter la requête
            ResultSet rs = pstmt.executeQuery();

            // Vérifier si l'utilisateur existe
            if (rs.next()) {
                out.println("<h2>Connexion réussie!</h2>");
            } else {
                out.println("<h2>Échec de la connexion. Veuillez vérifier vos informations d'identification.</h2>");
            }

            // Fermer les ressources
            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h2>Erreur de connexion à la base de données Oracle.</h2>");
        }

        out.println("</body></html>");
    }
}
