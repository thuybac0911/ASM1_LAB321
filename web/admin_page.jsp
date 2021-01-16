<%-- 
    Document   : admin_page
    Created on : Jan 12, 2021, 1:14:28 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null or sessionScope.LOGIN_USER.roleID != 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <h1>Welcome ${sessionScope.LOGIN_USER.fullname}</h1>
        <c:url var="logout" value="MainController" >
            <c:param name="action" value="Logout" ></c:param>
        </c:url>
        <a href="${logout}">Logout</a><br/>
        <c:url var="createFood" value="MainController" >
            <c:param name="action" value="createFood" ></c:param>
        </c:url>
        <a href="${createFood}">Create new Food</a><br/>
        <h3>Categories</h3>
        <c:forEach items="${applicationScope.LIST_CATE}" var="cateDTO" varStatus="counter">
            <c:url value="MainController" var="linkCate">
                <c:param name="action" value="getProduct"/>
                <c:param name="cateName" value="${cateDTO.cateName}"/>
                <c:param name="txtRoleID" value="${sessionScope.LOGIN_USER.roleID}"/>
            </c:url>
            <a href="${linkCate}">${cateDTO.cateName}</a>
        </c:forEach>
            <c:if test="${not empty sessionScope.LIST_PRO}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Create Date</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.LIST_PRO}" var="dto" varStatus="counter">
                        <tr>
                            <form action="MainController" method="POST">
                                <td>${counter.count}</td>
                                <td>${dto.productID}</td>
                                <td>${dto.productName}</td>
                                <td>${dto.price}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.description}</td>
                                <td>
                                    <img src="${dto.image}"/>
                                </td>
                                <td>${dto.createDate}</td>
                                <td>
                                    <input type="submit" name="action" value="Edit"/>
                                    <input type="hidden" name="txtProductID" value="${dto.productID}"/>
                                    <input type="hidden" name="txtProductName" value="${dto.productName}"/>
                                    <input type="hidden" name="txtPrice" value="${dto.price}"/>
                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}"/>
                                    <input type="hidden" name="txtDescription" value="${dto.description}"/>
                                    <input type="hidden" name="txtImage" value="${dto.image}"/>
                                    <input type="hidden" name="cboCateName" value="${dto.cateID}"/>
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="txtProductID" value="${dto.productID}"></c:param>
                                        <c:param name="cateName" value="${cateDTO.cateName}"/>
                                        <c:param name="txtRoleID" value="${sessionScope.LOGIN_USER.roleID}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>    
    </body>
</html>
