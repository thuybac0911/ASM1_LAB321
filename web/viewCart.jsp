<%-- 
    Document   : viewCart
    Created on : Jan 16, 2021, 2:06:04 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <h2>${sessionScope.LOGIN_USER.fullname}'s Cart</h2>
        <form action="MainController" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Image</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.shoppingCart.shoppingCart.values()}" var ="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.productName}</td>
                            <td>${dto.price}</td>
                            <td>
                                <input type="hidden" name="txtProductID" value="${dto.productID}" />
                                <input type="text" name="txtQuantity" value="${dto.quantity}" />
                            </td>
                            <td>${dto.description}</td>
                            <td>
                                <img src="${dto.image}" width="90" height="140"/>                               
                            </td>
                            <td>
                                <input type="checkbox" name="chkRemove" value="${dto.productID}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td>
                            <a href="user_page.jsp">Continue shopping</a>
                        </td>
                        <td>
                            ${sessionScope.shoppingCart.total}
                        </td>
                        <td>
                            <input type="submit" name="action" value="Update Quantity" />
                        </td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="submit" name="action" value="Remove Food" />
                        </td>
                    </tr>
                </tbody>
            </table>
                        ${requestScope.ERROR_CHECKOUT}
                        <input type="submit" name="action" value="Order" />
        </form>                       
    </body>
</html>
