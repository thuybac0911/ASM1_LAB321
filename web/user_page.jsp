<%-- 
    Document   : user_page
    Created on : Jan 12, 2021, 1:14:56 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.roleID != 'US'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <h1>Welcome ${sessionScope.LOGIN_USER.fullname}</h1>
        <c:url var="logout" value="MainController" >
            <c:param name="action" value="Logout" ></c:param>
        </c:url>
        <a href="${logout}">Logout</a><br/>
    </body>
</html>
