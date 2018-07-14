<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Dreamerk
  Date: 15.02.2018
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <spring:form modelAttribute="newUser" method="post" action="/user-system/users/registerNewUser">
        <spring:input path="name"/>
        <spring:input path="password"/>
        <spring:button>register user</spring:button>
    </spring:form>
</body>
</html>
