<%@ page import="com.studyeasy.websitewithsql.entity.User" %>
<%@ page import="java.util.List" %> <%--
  Created by IntelliJ IDEA.
  User: keshavjrall
  Date: 6/19/24
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="include/header.jsp"/>

<h3>List users</h3>

<table border="1">
    <thead>
    <th>User Id</th>
    <th>Username</th>
    <th>Email</th>
    <th>Operation</th>
    </thead>

    <%
        List<User> listusers = (List) request.getAttribute("listusers");
        for (User _user : listusers) {
            out.print("<tr>");
            out.print("<td>" + _user.getUser_id() + "</td>");
            out.print("<td>" + _user.getUsername() + "</td>");
            out.print("<td>" + _user.getEmail() + "</td>");
            out.print("<td> <a href="+ request.getContextPath()+"/site?page=updateuser&user_id="+_user.getUser_id() + "&username=" + _user.getUsername() + "&email=" + _user.getEmail()+"> Update</a>");
            out.print(" | ");
            out.print("<a href="+ request.getContextPath()+"/site?page=deleteuser&user_id="+_user.getUser_id() +"> Delete</a>  </td>");
            out.print("</tr>");
        }
    %>

</table>

<jsp:include page="include/footer.jsp"/>