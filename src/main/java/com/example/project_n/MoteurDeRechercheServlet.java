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

@WebServlet("/RechercherArticlesServlet")
public class MoteurDeRechercheServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "hr";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recherche = request.getParameter("recherche");
        List<Map<String, String>> resultatsRecherche = rechercherArticlesParNom(recherche);
        request.setAttribute("resultatsRecherche", resultatsRecherche);

        RequestDispatcher dispatcher = request.getRequestDispatcher("resultatsRecherche.jsp");
        dispatcher.forward(request, response);
    }

    private List<Map<String, String>> rechercherArticlesParNom(String recherche) {
        List<Map<String, String>> resultatsRecherche = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM ARTICLES WHERE LOWER(NOwMARTICLE) LIKE LOWER(?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, "%" + recherche + "%");

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            Map<String, String> article = new HashMap<>();
                            article.put("photo", resultSet.getString("IMGARTICLE"));
                            article.put("nomArticle", resultSet.getString("NOMARTICLE"));
                            article.put("prix", resultSet.getString("PRIX"));

                            // Ajoutez d'autres propriétés de l'article si nécessaire

                            resultatsRecherche.add(article);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Gérer l'exception de manière appropriée en production
        }

        return resultatsRecherche;
    }
}
