<%-- 
    Document   : SearchWalmart.jsp
    Created on : Mar 22, 2016, 6:07:16 PM
    Author     : scr08
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Walmart Search</h1>
        <form action="GetSearchResults" method="POST">
            <input type="hidden" value="<%= request.getParameter("SelectedSearchResults")%>" name ="SelectedSearchResults">
            Enter Search: <input type="text" name="SearchString"/>
            <input type="submit" name="submit">
        </form>
    </body>
</html>
