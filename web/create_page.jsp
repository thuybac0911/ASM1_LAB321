<%-- 
    Document   : create_page
    Created on : Jan 12, 2021, 1:39:13 PM
    Author     : Thúy Bắc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREATE FOOD Page</title>
    </head>
    <body>
        <h1>CREATE NEW FOOD</h1>
        <form action="MainController" method="POST">
            Food ID: <input type="text" name="txtProductID" value="${param.txtProductID}" required="true"/>
            <font color="red">
            ${requestScope.ERROR.productIDError}
            </font><br/>
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
            Image: <input type="file" name="txtImage" value="${param.txtImage}" required="true"/>
            <br/>
            Cate Name: <select name="cboCateID">
                    <option>Drinks</option>
                    <option>Cakes</option>
                    <option>Candies</option>
                    </select>
            <br/>
            <input type="submit" name="action" value="Create_Product" />
        </form>
        
    </body>
</html>
