
<jsp:include page="include/header.jsp"/>

<form action="${pageContext.request.contextPath}/site" method="post">

    User name: <input type="text" name="username" value="${param.username}" required = "required"/><br>
    Email : <input type="text" name="email" value="${param.email}" required="required"/><br>
    <input type="hidden" name="form" value="updateUserOperation"/>
    <input type="hidden" name="user_id" value="${param.user_id}"/>
    <input type="submit" value="Update"/>


</form>

<jsp:include page="include/footer.jsp"/>