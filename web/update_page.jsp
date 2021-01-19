<%-- 
    Document   : update_page
    Created on : Jan 15, 2021, 12:46:30 PM
    Author     : Thúy Bắc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UPDATE Page</title>
    </head>
    <body>
        <h1>UPDATE PRODUCT</h1>
        <form action="MainController" method="POST">
            
            <br/>
            Food ID: <input type="text" name="txtProductID" value="${param.txtProductID}" readonly="true"/>
            <br/>
            Name: <input type="text" name="txtProductName" value="${param.txtProductName}" required="true"/>
            <font color="red">
            ${requestScope.ERROR.productNameError}
            </font><br/>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}" required="true"/>
            <font color="red">
            ${requestScope.ERROR.priceError}
            </font><br/>
            Quantity: <input type="text" name="txtQuantity" value="${param.txtQuantity}" required="true"/>
            <font color="red">
            ${requestScope.ERROR.quantityError}
            </font><br/>
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}" required="true"/>
            <font color="red">
            ${requestScope.ERROR.descriptionError}
            </font><br/>
            Image: <input type="file" name="txtImage" value="${param.txtImage}"/><br/>
            Cate Name: <select name="cboCateID">
                    <option>Drinks</option>
                    <option>Cakes</option>
                    <option>Candies</option>
                    </select>
            <br/>
            <input type="hidden" name="txtRoleID" value="${sessionScope.LOGIN_USER.roleID}"/>
            <input type="submit" name="action" value="Update" />
        </form>
            <a href="admin_page.jsp">Back To ADMIN Page</a>
    </body>
</html>
