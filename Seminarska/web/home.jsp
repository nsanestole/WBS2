<%-- 
    Document   : home
    Created on : Aug 23, 2015, 6:37:06 PM
    Author     : Stole
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        ArrayList<String> lista = (ArrayList<String>) request.getAttribute("gradovi");
        %>
        <%
        for(String item : lista)
        {
            %>
            <div class="text-center panel panel-body"> <%=item%></div>
            <%
        }
        
        %>
    </body>
</html>
