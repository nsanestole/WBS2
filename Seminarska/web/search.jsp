<%-- 
    Document   : search
    Created on : Sep 1, 2015, 3:39:42 PM
    Author     : martin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Grad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1><span>Result from search:</span></h1>
        <div class="row">
            
            <% ArrayList<Grad> lista = (ArrayList<Grad>) request.getAttribute("gradovi");
                for (Grad item : lista) {

            %>

            <div class="panel panel-info col-md-3">
                <div class="panel-heading text-center">
                   
                    <span><%=item.getName()%></span>
                </div>
                <div class="panel-body">
                    <image src="<%=item.getImgUrl()%> " height="150" width="150"/>
                </div>
                <div>
                    <a href="/Seminarska/details?grad=<%=item.getName()%>">Show more details </a>
                </div>
            </div>

            <% }%>
        </div>
        </div>
    </body>
</html>
