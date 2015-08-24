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
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% ArrayList<Grad> lista = (ArrayList<Grad>)request.getAttribute("gradovi");
        for(Grad item : lista)
        {
            
        %>
        <div>
            <div class="panel panel-info col-md-4">
                <div class="panel-heading">
                    <%String str = item.getName();
                            String []parts = str.split("/");
                            %>
                    <span><%=parts[parts.length - 1]%></span>
                </div>
                <div class="panel-body">
                    <image src="<%=item.getImgUrl() %> " height="150" width="150"/>
                </div>
                <div>
                    <a href="/Seminarska/details?grad=<%=parts[parts.length - 1]%>">Show more details </a>
                </div>
            </div>
        </div>
            <% } %>
                
    </body>
</html>
