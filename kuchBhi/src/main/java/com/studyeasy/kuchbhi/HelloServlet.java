package com.studyeasy.kuchbhi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("login")) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action){
            case "authenticate":
                authenticate(request, response);
                break;
            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    protected void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("admin")) {
            request.getSession().invalidate();
            HttpSession newSession = request.getSession();
            newSession.setMaxInactiveInterval(500);
            newSession.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/hello-controller?action=memberArea");

        } else {
            response.sendRedirect(request.getContextPath() + "/hello-servlet?action=login");
        }

    }


        public void destroy() {
        System.out.println("----------Destroy Servlet----------");
    }
}