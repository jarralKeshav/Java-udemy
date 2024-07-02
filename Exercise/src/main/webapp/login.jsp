<%--
  Created by IntelliJ IDEA.
  User: keshavjrall
  Date: 6/17/24
  Time: 2:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>



<form action="<%= request.getContextPath()%>" method="post">


Username: <input type="text" name="username">
<br/>
Password: <input type="password" name="password">
<br/>
<input type="submit" value="Login">
</form>

</body>
</html>
