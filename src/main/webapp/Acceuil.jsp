
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="CSS/index.css"/>
</head>
<body>
<header>
    <nav>
        <div class="ok">
            <img class="ee" src="IMAGES/Logo.png" alt="logo">
            <form action="" method="POST">
                <div>
                    <input type="search" placeholder="Rechercher un produit">
                </div>
            </form>
        </div>
        <div class="te">
            <ul>
                <li>
                    <a href="connexion.jsp">Connexion<img src="IMAGES/profil.PNG"></a>
                </li>
                <li>
                    <a href="#">Tutoriel<img src="IMAGES/tuto.PNG"></a>
                </li>
                <li>
                    <a href="#">Panier<img src="IMAGES/panier.PNG"></a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="blue">

    </div>
</header>
<div class="grand">
    <div class="petit1">
        <h1><strong>SITE DE VENTE EN </br>LIGNE DE </strong>BÂCHES</h1>
        <p>Nous mettons à votre disposition, des produits du</br> secteur le l'évènementiel
            pour vous permettre de</br> vous épanouir et de vous protéger lors de vos</br> différents évènements.
        </p>
        <div class="toi">
            <a href="inscription.jsp"><input class="connect" type="button" value="S'inscrire"></a>
        </div>
    </div>
</div>

<h1 class="proj"><strong class="ht">NOS PROJETS</strong></h1>

<div class="oki">
    <%
        List<Map<String, String>> articles = (List<Map<String, String>>) request.getAttribute("articles");

        if (articles != null) {
            for (Map<String, String> article : articles) {
    %>


    <div class="article">
        <img src="photoProduit/<%= article.get("photo") %>" alt="<%= article.get("nomArticle") %>">
        <div class="art-bas">
            <div class="ok">
                <h3><%= article.get("nomArticle") %></h3>
                <p>Prix: <%= article.get("prix") %></p>
            </div>
            <a href="#">
                <button>
                    Ajouter au panier
                </button>
            </a>
        </div>

        <!-- Ajouter les autres propriétés ici -->
    </div>
    <%
            }
        }
    %>

</div>






</body>
</html>