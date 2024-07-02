<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Screen</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/hello-servlet" method="post">


    Username: <input type="text" name="username"/><br/>

    Password: <input type="password" name="password"/><br/>
    <input type="hidden" name="action" value="authenticate">
    <input type="submit" value="Login">



</form>




</body>
</html>