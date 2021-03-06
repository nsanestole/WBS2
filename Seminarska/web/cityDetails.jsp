
<%-- 
    Document   : home
    Created on : Aug 23, 2015, 6:37:06 PM
    Author     : Stole
--%>


<%@page import="model.Grad"%>
<%@page import="model.CityDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <style>
            .carousel-inner > .item > img,
            .carousel-inner > .item > a > img {
                width: 70%;
                height: 450px;
                margin: auto;
            }
        </style>

        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/Seminarska/index">GeoMak</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


                        <ul class="nav navbar-nav navbar-right">
                            <form class="navbar-form navbar-left" method="post" action="/Seminarska/search">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="search" id="search" placeholder="Search by settlement">
                                </div>
                                <input type="submit" class="btn btn-default" value="Submit">

                                <% 
                                   String username = (String)session.getAttribute("username");
             
                                       if(username != null){
                                %>
                                <a type="submit" class="btn btn-info" href="/Seminarska/logout" ><%=username%></a>

                                <a type="submit" class="btn btn-info" href="/Seminarska/add">Add settlement</a>

                                <%
                                    }
                                    else {
                                %>
                                <a type="submit" class="btn btn-default" href="/Seminarska/login">Login</a>
                                <% } %>
                            </form>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group">
                        <% ArrayList<Grad> lista = (ArrayList<Grad>)request.getAttribute("gradovi");
        for(Grad item : lista)
        {
            
                        %>
                        <%String str = item.getName();
                                            String []parts = str.split("/");
                               
                                           String str2 = parts[parts.length -1 ].split(",")[0];
                           
                                           String str3 =  str2.replaceFirst("\\_\\(", " ");
                           
                                           String[] str4 = str3.split(" ");
                           
                                           String grad = str4[0];
                        %>
                        <a class="list-group-item" href="/Seminarska/details?grad=<%=grad%>">



                            <span><%=grad%></span>

                        </a>
                        <% } %>
                    </div>
                </div>
                <div class="col-md-9">
                    <% CityDetail city = (CityDetail)request.getAttribute("city"); %>
                    <div class="row">
                        <iframe

                            width="600"
                            height="450"
                            frameborder="0" style="border:1"
                            src="https://www.google.com/maps/embed/v1/view?key=AIzaSyAk3XKgfAnGHS8G-WATnb-rLCwS0wxid_Q&center=<%=city.getLat() %>,<%=city.getLongt() %>
                            &zoom=10
                            " allowfullscreen>
                        </iframe> 

                        </br>
                        <div class="row">

                            <div class="panel panel-defaul panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Ime na gradot</h3>
                                </div>
                                <div class="panel-body">
                                    <span><%=city.getName()%></span>
                                </div>
                            </div>
                            <div class="panel panel-defaul panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Broj na populacija</h3>
                                </div>
                                <div class="panel-body">
                                    <span><%=city.getPopulation()%></span>
                                </div>
                            </div>
                            <div class="panel panel-defaul panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Opis za gradot</h3>
                                </div>
                                <div class="panel-body">
                                    <span><%=city.getAbstract()%></span>
                                </div>
                            </div>  
                            <div class="panel panel-defaul panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Gradonacalnik</h3>
                                </div>
                                <div class="panel-body">
                                    <span><%=city.getLeader()%></span>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
