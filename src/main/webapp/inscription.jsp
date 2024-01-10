<%--
  Created by IntelliJ IDEA.
  User: CZS
  Date: 03/01/2024
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/inscription.css">
    <title>Title</title>
</head>
<body>
        <div class="droite">
            <h2>
                ACHETER EN LIGNE SUR <mark>EVENTS STORES</mark>
            </h2>
            <p>
                <mark>EVENTS STORES </mark> met à votre disposition des articles liés au secteur de <br>l'évènementiel à des prix
                justes et abordables.
            </p>
            <div class="image">
                <img src="IMAGES/noo.svg" alt="shop">
            </div>
        </div>
        <div class="gauche">
            <form action="InscriptionServlet" method="post">
                <h4>Inscrivez vous sur <mark>EVENTS STORES</mark></h4>
                <input type="text" placeholder="Nom" name="NomClient">
                <input type="text" placeholder="Prénom" name="PrenClient">
                <input type="email" placeholder="Email" name="MailClient">
                <input type="number" placeholder="Telephone" minlength="10" maxlength="10" name="TelClient">
                <input type="password" placeholder="Mot de passe" name="MotDePasse">
                <input type="password" placeholder="Confirmer mot de passe" name="ConfirmationMotDePasse">

                <div class="boutton">
                    <button type="submit"> S'inscrire </button>
                </div>
                <p>Avez-vous dejà un compte ? <a href="connexion.jsp">Se connecter</a></p>
            </form>
        </div>
</body>
</html>
