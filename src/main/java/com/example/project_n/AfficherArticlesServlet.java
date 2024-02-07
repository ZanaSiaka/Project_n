package com.example.project_n;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AfficherArticlesServlet")
public class AfficherArticlesServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "hr";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> articles = recupererArticlesDepuisBaseDeDonnees();
        request.setAttribute("articles", articles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Acceuil.jsp");
        dispatcher.forward(request, response);
    }

    private List<Map<String, String>> recupererArticlesDepuisBaseDeDonnees() {
        List<Map<String, String>> articles = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM ARTICLES";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            Map<String, String> article = new HashMap<>();
                            article.put("photo", resultSet.getString("imgarticle"));
                            article.put("nomArticle", resultSet.getString("nomarticle"));
                            article.put("prix", resultSet.getString("prix"));


                            // Ajoutez d'autres propriétés de l'article ici

                            articles.add(article);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Ajoutez une gestion d'erreur appropriée en production
        }

        return articles;
    }
}
