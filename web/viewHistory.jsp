<%-- 
    Document   : viewHistory
    Created on : Jan 19, 2021, 7:59:56 AM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HISTORY Page</title>
    </head>
    <body>
        <h1>Manage Order</h1>
        <c:if test="${ not empty sessionScope.LIST_ORDER}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>OrderID</th>
                        <th>DateOfCreate</th>
                        <th>Total Price</th>
                        <th>Status</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.LIST_ORDER}" var="list" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${list.orderID}</td>
                            <td>${list.dateOfCreate}</td>
                            <td>${list.totalPrice}</td>
                            <td>${list.status}</td>
                            <td>
                                <c:url var="details" value="MainController">
                                    <c:param name="txtOrderID" value="${list.orderID}"/>
                                    <c:param name="txtTotalPrice" value="${list.totalPrice}"/>
                                    <c:param name="action" value="viewDetails"/>
                                </c:url>
                                <a href="${details}">View Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                </tbody>
            </table>
        </c:if>
    </body>
</html>
