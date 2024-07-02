<%--
  Created by IntelliJ IDEA.
  User: keshavjrall
  Date: 6/17/24
  Time: 2:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Member file</title>
</head>
<body>
<%
    String username = null, sessionId = null;

  if(request.getSession().getAttribute("username")== null){
      response.sendRedirect("/login.js");
  }else {
      username = request.getSession().getAttribute("username").toString();
      sessionId = request.getSession().getId();
  }

%>


UserName: <%= username %><br/>
SessionID: <%=sessionId %><br/>
<h2>
    This is the member jsp file
</h2>

<form action="<%=request.getContextPath()%>/hello-controller" method="get">
    <input type="hidden" name="action" value="destroy">
    <input type="submit" value="Logout">

</form>


</body>
</html>
