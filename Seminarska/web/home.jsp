<%-- 
    Document   : home
    Created on : Aug 23, 2015, 6:37:06 PM
    Author     : Stole
--%>


<%@page import="model.Grad"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% ArrayList<Grad> lista = (ArrayList<Grad>)request.getAttribute("gradovi");
        for(Grad item : lista)
        {
            
        %>
        <div class="panel panel-default">
            <div class="panel-heading">
                <span><%=item.getName() %></span>
            </div>
            <div class="panel-body">
                <image src="<%=item.getImgUrl() %>"/>
            </div>
        </div>
            
                <% }%>
    </body>
</html>