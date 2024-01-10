package com.example.project_n;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class HttpServletRequest {

    public String getParameter(String numClient) {
        return numClient;
    }

    public HttpSession getSession() {

        return null;
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    public void setAttribute(String errorMessage, String s) {
    }
}
