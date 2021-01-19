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
        <c:url value="MainController" var="linkLogin">
            <c:param name="action" value="getLinkLogin"/>
        </c:url>
        <a href="${linkLogin}">Login</a><br/>
        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/Assignment1_BacLTT/LoginGoogleController&response_type=code
    &client_id=763088501197-d51etv13quvfvquc8esi4i9eliv1mohl.apps.googleusercontent.com">Login With Google</a>  
        <br/>    
        <a href="search.jsp">Search Product</a>
        <h3>Categories</h3>
        <c:forEach items="${applicationScope.LIST_CATE}" var="cateDTO" varStatus="counter">
            <c:url value="MainController" var="linkCate">
                <c:param name="action" value="getProduct"/>
                <c:param name="cateName" value="${cateDTO.cateName}"/>

            </c:url>
            <a href="${linkCate}">${cateDTO.cateName}</a>
            
        </c:forEach>
            
            <c:if test="${not empty sessionScope.LIST_PRO}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.LIST_PRO}" var="dto" varStatus="counter">
                    <tr>
                    <form action="MainController" method="POST">
                        <td>${counter.count}</td>
                        <td>${dto.productName}</td>
                        <td>${dto.price}</td>
                        <td>${dto.description}</td>
                        <td>
                            <img src="${dto.image}" height="150" width="150"/>
                        </td>
                        </form>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>    
    </body>
</html>
