
<jsp:include page="include/header.jsp"/>

<form action="${pageContext.request.contextPath}/site" method="post">

    User name: <input type="text" name="username" required = "required"/><br>
    Email : <input type="text" name="email" required="required"/><br>
            <input type="hidden" name="form" value="addUserOperation"/>
            <input type="submit" value="Add User"/>


</form>

<jsp:include page="include/footer.jsp"/>