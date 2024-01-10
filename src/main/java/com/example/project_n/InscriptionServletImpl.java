package com.example.project_n;

import java.io.IOException;

public class InscriptionServletImpl extends InscriptionServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, String NumClient, String NomClient, String PrenClient, String MailClient, int TelClient, String MotDePasse) throws ServletException, IOException {
        super.doPost(request, response, NumClient, NomClient, PrenClient, MailClient, TelClient, MotDePasse);
    }
}
