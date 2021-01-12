<%-- 
    Document   : login
    Created on : Jan 5, 2021, 3:00:41 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN Page</title>
    </head>
    <body>
        <h1>LOGIN</h1>
        <c:url value="MainController" var="homePage">
            <c:param name="action" value="homePage"/>
        </c:url>
        <a href="${homePage}">Back To Home Page</a>
        <form action="MainController" method="POST">
            UserID: <input type="text" name="txtUserID" value="${param.txtUserID}" required="true" /><br/>
            Password: <input type="password" name="txtPassword" value="${param.txtPassword}" required="true" /><br/>
            <input type="submit" name="action" value="Login" />
        </form>
    </body>
</html>
