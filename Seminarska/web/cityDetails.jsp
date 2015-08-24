<%-- 
    Document   : cityDetails
    Created on : Aug 24, 2015, 7:49:01 PM
    Author     : martin
--%>

<%@page import="model.Grad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Grad g = (Grad)request.getAttribute("grad"); %>
        <h1><%=g.getPopulation() %></h1>
    </body>
</html>
