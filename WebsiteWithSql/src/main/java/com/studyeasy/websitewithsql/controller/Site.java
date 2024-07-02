package com.studyeasy.websitewithsql.controller;

import com.studyeasy.websitewithsql.entity.User;
import com.studyeasy.websitewithsql.model.UsersModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Site", value = "/site")
public class Site extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String page = request.getParameter("page").toLowerCase();
        switch (page) {
            case "listusers":
                listusers(request, response);
                break;
            case "adduser":
                addusers(request, response);
                break;
            case "updateuser":
                updateuser(request, response);
                break;

             case "deleteuser":
                 new UsersModel().deleteUser(Integer.parseInt(request.getParameter("user_id")));
                 listusers(request, response);
                break;

            default:
                request.setAttribute("title", "ERROR");
                request.getRequestDispatcher("error.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String form = request.getParameter("form").toLowerCase();
        switch (form) {
            case "adduseroperation":
                User user = new User(request.getParameter("username"), request.getParameter("email"));
                new UsersModel().addUser(user);
                listusers(request, response);
                break;
             case "updateuseroperation":
                User updated_user = new User(Integer.parseInt(request.getParameter("user_id")) ,request.getParameter("username"), request.getParameter("email"));
                new UsersModel().updateUser(updated_user);
                listusers(request, response);
                break;


            default:
                request.setAttribute("title", "ERROR");
                request.getRequestDispatcher("error.jsp").forward(request, response);

        }
    }

    protected void listusers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = new ArrayList<User>();
        users = new UsersModel().listusers();
        req.setAttribute("listusers", users);
        req.setAttribute("title", "LIST USERS");
        req.getRequestDispatcher("listusers.jsp").forward(req, resp);
    }


    protected void addusers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "AddUsers");
        request.getRequestDispatcher("adduser.jsp").forward(request, response);
    }

    protected void updateuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "UpdateUsers");
        request.getRequestDispatcher("updateuser.jsp").forward(request, response);
    }

    public void destroy() {
    }
}