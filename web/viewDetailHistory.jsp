<%-- 
    Document   : viewDetailHistory
    Created on : Jan 19, 2021, 9:48:02 AM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VIEW DETAIL Page</title>
    </head>
    <body>
        <h1>Detail order: ${param.txtOrderID}</h1>
        <c:if test="${not empty sessionScope.LIST_ORDER_DETAIL}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.LIST_ORDER_DETAIL}" var="list" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${list.name}</td>
                            <td>${list.price}</td>
                            <td>${list.quantity}</td>
                            <td>
                                <img src="${list.image}" height="150" width="150"/>
                            </td>
                        </tr>
                    </c:forEach>
                    
                </tbody>
            </table>
        </c:if>
        <h2>
            Total: ${param.txtTotalPrice}
        </h2>
        <a href="user_page.jsp">Back to USER page</a>
    </body>
</html>
