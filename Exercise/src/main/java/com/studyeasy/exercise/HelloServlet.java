package com.studyeasy.exercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.getWriter().println(request.getContextPath());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        if(username.equals("admin") && password.equals("admin")) {
//            req.getSession().invalidate();
//            HttpSession newSession = req.getSession(true);
//            newSession.setMaxInactiveInterval(500);
//            resp.sendRedirect("member.jsp");
//        }else {
//            resp.sendRedirect("login.jsp");
//        }



    }

    public void destroy() {
    }
}