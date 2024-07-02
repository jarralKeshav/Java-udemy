package com.studyeasy.kuchbhi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "helloController", value = "/hello-controller")
public class Controller extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action.equals("destroy")) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/hello-servlet?action=login");
        }
        if (action.equals("memberArea")) {
            req.getRequestDispatcher("/member.jsp").forward(req,resp);
        }
    }

    public void destroy() {
        System.out.println("----------Destroy Servlet----------");
    }
}