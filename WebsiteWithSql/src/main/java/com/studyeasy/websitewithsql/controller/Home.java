package com.studyeasy.websitewithsql.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String page = request.getParameter("page").toLowerCase();
        switch (page) {
            case "home":
                request.setAttribute("title", "HOMEPAGE");

                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;


            default:
                request.setAttribute("title", "ERROR");
                request.getRequestDispatcher("error.jsp").forward(request, response);

        }

    }



    public void destroy() {
    }
}