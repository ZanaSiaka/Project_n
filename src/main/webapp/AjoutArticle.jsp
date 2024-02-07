<%--
  Created by IntelliJ IDEA.
  User: CZS
  Date: 21/01/2024
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ArticleAjout</title>
    <link rel="stylesheet" href="CSS/AjoutArticle.css">
</head>
<body>
<div class="droite">
    <h2>
        ACHETER EN LIGNE SUR <mark>EVENTS STORES</mark>
    </h2>
    <p>
        <mark>EVENTS STORES </mark> met à votre disposition des articles liés au secteur de <br>l'évènementiel à des prix justes et abordables.
    </p>
    <div class="image">
        <img src="IMAGES/noo.svg" alt="shop">
    </div>
</div>
<div class="gauche">
    <form action="AjouterArticleServlet" METHOD="post" enctype="multipart/form-data">
        <h4>Inscrivez vous sur <mark>EVENTS STORES</mark></h4>
        <input type="text" placeholder="Nom de l'article" name="NomArticle">

        <input type="number" placeholder="quantité" name="QteArticle">
        <input type="number" placeholder="PriX" name="Prix">
        <textarea placeholder="Description" name="DescArticle"></textarea>
        <input type="file" placeholder="Image" name="ImgArticle">
        <div class="boutton">
            <button>S'inscrire</button>
        </div>
        <p>Avez-vous dejà un compte ? <a href="connexion.jsp">Se connecter</a></p>
        <p>Vous n'etes pas un client ? <a href="Choix.html">Cliquez ici</a></p>
    </form>
</div>
</body>
</html>