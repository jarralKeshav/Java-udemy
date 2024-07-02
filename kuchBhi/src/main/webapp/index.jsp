
<jsp:include page="include/header.jsp">
   <jsp:param name="titlePage" value="Homepage"/>
    
</jsp:include>


<a href="<%=request.getContextPath()%>/hello-servlet?action=login"><h1>Login</h1></a>


<jsp:include page="include/footer.jsp"/>