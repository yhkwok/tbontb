<%-- 
    Document   : Home
    Created on : Mar 22, 2016, 6:05:37 PM
    Author     : scr08
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <form action="SearchWalmart.jsp" method="POST">
            <input type="hidden" name="GoodSearch" value="true"/>
            <button type="submit" value="" name="SelectedSearchResults">Create a New Poll</button>
        </form>
    </body>
</html>
