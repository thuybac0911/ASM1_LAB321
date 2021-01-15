<%-- 
    Document   : search
    Created on : Jan 7, 2021, 1:50:32 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>HANA SHOP</h1>
        <form action="MainController">
            Categories: <select name="cboCateName">
                            <option></option>
                            <option>Drinks</option>
                            <option>Cakes</option>
                            <option>Candies</option>
                        </select>
            Name: <input type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <%--
            Price: From: <input type="text" name="txtMin" value="${param.txtMin}" />  To: <input type="text" name="txtMax" value="${param.txtMax}"/><br/>
            --%>
            <input type="submit" name="action" value="Search" />
            <input type="hidden" name="txtRoleID" value="${sessionScope.LOGIN_USER.roleID}"/>
        </form>
    
            <c:if test="${sessionScope.SEARCH_LIST_FOOD !=null}">
            <c:if test="${not empty sessionScope.SEARCH_LIST_FOOD}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th>Create Date</th>
                                <th>Category</th>
                                <th>Image</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" varStatus="counter" items="${sessionScope.SEARCH_LIST_FOOD}">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${list.productName}</td>
                                    <td>${list.price}</td>
                                    <td>${list.description}</td>
                                    <td>${list.createDate}</td>
                                    <td>${list.cateID}</td>
                                    <td>
                                        <img src="${list.image}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                </table>
                </c:if>
            </c:if>
            <a href="index.jsp">Back To Home Page</a>    
    </body>
</html>
