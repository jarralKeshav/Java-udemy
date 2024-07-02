<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
It's Home
<br/>
<a href="demo">demo</a>
<% String contextPath =  request.getContextPath(); %>
<%= contextPath %>

<a href="<% request.getContextPath(); %>/demo?page=login">login</a><br/>
<%--<a href="<% request.getContextPath(); %> /Demo?page=signUp">signUp</a><br/>--%>
<%--<a href="<% request.getContextPath(); %> /Demo?page=about">About</a>--%>

</body>
</html>