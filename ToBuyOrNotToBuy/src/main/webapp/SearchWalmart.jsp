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
        <h1>Walmart Search</h1>
        <form action="GetSearchResults" method="POST">
            <input type="hidden" value="<% request.getParameter("SelectedSearchResults"); %>" name ="SelectedSearchResults">
            Enter Desired Poll Name: <input type="text" name="PollName" value="${param["PollName"]}"/>
            <br/>
            ${param["PollName"]}
            Enter Search: <input type="text" name="SearchString"/>
            <br/>
            <input type="submit" name="submit">
        </form>
            <c:if test="${param.SelectedSearchResults} == false">
                No Results Found
            </c:if>
                 <% 
 
            // you can get an enumeratable list 
            // of parameter keys by using request.getParameterNames() 
            Enumeration en = request.getParameterNames();
 
            // enumerate through the keys and extract the values 
            // from the keys! 
            while (en.hasMoreElements()) {
                String parameterName = (String) en.nextElement();
                String parameterValue = request.getParameter(parameterName);
                out.println(parameterName+":"+parameterValue+"<br />");
            }
 
            // now call your jsp file (from a browser and add on some paramters) 
            // file.jsp?a=12341234&b=apple&c=1.21gigawatts 
 
        %> 
    </body>
</html>
