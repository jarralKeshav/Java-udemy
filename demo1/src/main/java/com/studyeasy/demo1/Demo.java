package com.studyeasy.demo1;

//package com.studyeasy.demo1;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "demo", value = "/demo")
public class Demo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        // Hello
        String param = request.getParameter("page");

        if (param == null) {}
//        if (param.equals("login")){
//            getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
//        }




    }

    public void destroy() {
    }
}