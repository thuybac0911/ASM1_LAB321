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
        <form action="MainController">
            Categories: <select name="cboCateName" >
                            <option></option>
                            <option>Drinks</option>
                            <option>Cakes</option>
                            <option>Candies</option>
                        </select>
            Name: <input type="text" name="txtSearch" value="${param.txtSearch}" /><br/>
            <%--
            Price: From: <input type="text" name="txtMin" value="${param.txtMin}" />  To: <input type="text" name="txtMax" value="${param.txtMax}"/><br/>
            --%>
            <input type="hidden" name="txtRoleID" value="${sessionScope.LOGIN_USER.roleID}"/>
            <input type="submit" name="action" value="Search" />
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
    </body>
</html>
