<%-- 
    Document   : SearchWalmart.jsp
    Created on : Mar 22, 2016, 6:07:16 PM
    Author     : scr08
--%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Poll: <c:out value="${param.PollName}"/></h1>
        <form action="GetSearchResults" method="POST">
            <input type="hidden" value="${param.SelectedSearchResults}" name ="SelectedSearchResults">
            <input type="hidden" value="${param.PollName}" name ="SelectedSearchResults">
            
            Enter Search: <input type="text" name="SearchString"/>
            <br/>
            <input type="submit" name="submit">
            <c:out value="${param.GoodSearch}"/>
        </form>
            <c:if test="${param.GoodSearch} == false">
                No Results Found
            </c:if>
    </body>
</html>
