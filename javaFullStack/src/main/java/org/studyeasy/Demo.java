package org.studyeasy;

//package com.studyeasy.demo1;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "demo", value = "/demo")
public class Demo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        String param = request.getParameter("page");

        if (param.equals("login")){
            response.sendRedirect("login.jsp");
        }




    }

    public void destroy() {
    }
}