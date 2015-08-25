<%-- 
    Document   : cityDetails
    Created on : Aug 24, 2015, 7:49:01 PM
    Author     : martin
--%>

<%@page import="model.CityDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% CityDetail city = (CityDetail)request.getAttribute("city"); %>
        <div><span><%=city.getName()%></span></div>
        <div><span><%=city.getPopulation()%></span></div>
        <div><span><%=city.getAbstract()%></span></div>
        <div><span><%=city.getLeader()%></span></div>
    </body>
</html>
