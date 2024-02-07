package com.example.project_n;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PanierServlet")
public class PanierServlet extends HttpServlet {

    private static final String PANIER_KEY = "panier";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupération des paramètres du formulaire
        String action = request.getParameter("action");
        String NumArticle = request.getParameter("NumArticle");

        // Récupération du panier de l'utilisateur
        List<Article> panier = getPanier(request);

        // Identification de l'article dans le panier
        Article article = trouverArticleDansPanier(panier, NumArticle);

        // Gestion des actions sur le panier
        if (article != null) {
            switch (action) {
                case "augmenter":
                    augmenterQuantite(article);
                    break;
                case "diminuer":
                    diminuerQuantite(article);
                    break;
                case "supprimer":
                    supprimerArticle(panier, article);
                    break;
            }
        }

        // Enregistrement du panier dans la session
        setPanier(request, panier);

        // Redirection vers la page du panier
        response.sendRedirect("panier.jsp");
    }

    // Méthode pour augmenter la quantité d'un article dans le panier
    private void augmenterQuantite(Article article) {
        article.setQuantite(article.getQuantite() + 1);
        article.setPrixTotal(article.getPrix() * article.getQuantite());
    }

    // Méthode pour diminuer la quantité d'un article dans le panier
    private void diminuerQuantite(Article article) {
        if (article.getQuantite() > 1) {
            article.setQuantite(article.getQuantite() - 1);
            article.setPrixTotal(article.getPrix() * article.getQuantite());
        } else {
            // Si la quantité atteint 1, supprimer l'article du panier
            article.setQuantite(0);
            article.setPrixTotal(0);
        }
    }

    // Méthode pour supprimer un article du panier
    private void supprimerArticle(List<Article> panier, Article article) {
        panier.remove(article);
    }

    // Méthode pour récupérer le panier de l'utilisateur
    private List<Article> getPanier(HttpServletRequest request) {
        // Récupération du panier de la session
        List<Article> panier = (List<Article>) request.getSession().getAttribute(PANIER_KEY);

        // Si le panier n'existe pas, le créer
        if (panier == null) {
            panier = new ArrayList<>();
        }

        return panier;
    }

    // Méthode pour enregistrer le panier dans la session
    private void setPanier(HttpServletRequest request, List<Article> panier) {
        // Enregistrement du panier dans la session
        request.getSession().setAttribute(PANIER_KEY, panier);
    }

    // Méthode pour trouver un article dans le panier par son identifiant
    private Article trouverArticleDansPanier(List<Article> panier, String idArticle) {
        for (Article article : panier) {
            if (article.getIdArticle().equals(idArticle)) {
                return article;
            }
        }
        return null;
    }
}
