package com.example.project_n;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AjouterAuPanierServlet")
public class AjouterAuPanierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenez la session de l'utilisateur à partir de la requête
        HttpSession session = request.getSession();

        // Obtenez le panier de l'utilisateur depuis la session ou créez-le s'il n'existe pas encore
        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Obtenez l'ID de l'article à ajouter (vous devrez adapter cela à votre application)
        String articleId = request.getParameter("NumArticle");

        // Ajoutez l'article au panier
        cart.put(articleId, cart.getOrDefault(articleId, 0) + 1);

        // Mettez à jour le panier dans la session
        session.setAttribute("cart", cart);

        // Envoyez une réponse au client si nécessaire
        response.getWriter().write("Article ajouté au panier");
    }
}
