<%--
  Created by IntelliJ IDEA.
  User: CZS
  Date: 03/01/2024
  Time: 01:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Connexion</title>
    <link rel="stylesheet" href="CSS/connexion.css"/>
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
        <form action="ConnexionServlet" method="post">
            <h4>Connecter vous à votre compte </h4>
            <input type="email" placeholder="Email" name="EmailClient">
            <input type="password" placeholder="Mot de passe" name="MotDePasse">
            <div class="lien">
                <a href="modif.html">Mot de passe oublié ?</a>
            </div>
            <div class="boutton">
                <button type="submit">Se connecter</button>
            </div>
            <p>Vous n'avez pas encore de compte ? <a href="inscription.jsp">S'inscrire</a></p>
            <p>Vous n'êtes pas un client ? <a href="Choix.html">Cliquez ici</a></p>
        </form>
    </div>
</body>
</html>
