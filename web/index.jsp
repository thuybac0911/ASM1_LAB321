<%-- 
    Document   : index
    Created on : Jan 7, 2021, 12:49:25 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WELCOME Page</title>
    </head>
    <body>
        <h1>HANA SHOP</h1>
        <a href="login.jsp">Login</a>
        <a href="search.jsp">Search Product</a>
        <h3>Categories</h3>
        <c:forEach items="${applicationScope.LIST_CATE}" var="cateDTO" varStatus="counter">
            <c:url value="MainController" var="linkCate">
                <c:param name="action" value="getProduct"/>
                <c:param name="cateID" value="${cateDTO.cateID}"/>
            </c:url>
            <a href="${linkCate}">${cateDTO.cateName}</a>
        </c:forEach>
    </body>
</html>
