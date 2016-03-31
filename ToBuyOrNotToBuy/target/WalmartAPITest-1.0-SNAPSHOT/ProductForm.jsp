<%-- 
    Document   : ProductForm
    Created on : Mar 19, 2016, 10:53:24 PM
    Author     : scr08
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Form</title>
    </head>
    <body>
        <form action="DisplaySelected" method="POST">
            <input type="hidden" name="SelectedSearchResults" value="${SelectedSearchResults}" />
            <table>
                <thead>
                    <tr>
                        <td>
                            Key
                        </td>
                        <td>
                            Value
                        </td>
                    </tr>
                </thead>zz
                <tbody >
                    <c:forEach items="${Products}" var="ProductInfo">
                        <tr >
                            <td>
                                Item Name
                            </td>
                            <td>
                                ${ProductInfo.getName()}
                            </td>
                        </tr>
                        <tr >
                            <td>
                                Item Description
                            </td>
                            <td>
                                ${ProductInfo.getDescription()}
                            </td>
                        </tr >
                        <tr>
                            <td>
                                Price
                            </td>
                            <td>
                                ${ProductInfo.getCost()}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Image
                            </td>
                            <td>
                                <img src="${ProductInfo.getThumbImageURL()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Add to Poll: 
                            </td>
                            <td>
                                <button type="submit" name="submit" value="${ProductInfo.getJson()}">Add</button>
                            </td>
                        </tr> 
                        <tr>
                            <td>
                                <hr/>
                            </td>
                            <td>
                                <hr/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
