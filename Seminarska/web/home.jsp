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
                    <div class="row">
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
    <li data-target="#myCarousel" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
        <img src="<%=lista.get(0).getImgUrl() %>" alt="Chania">
        <div class="carousel-caption">
        <h3><%=lista.get(0).getName()%></h3>
      </div>
    </div>

    <div class="item">
      <img src="<%=lista.get(1).getImgUrl() %>"  alt="Chania">
      <div class="carousel-caption">
        <h3><%=lista.get(1).getName()%></h3>
      </div>
    </div>

    <div class="item">
      <img src="<%=lista.get(2).getImgUrl() %>"  alt="Flower">
      <div class="carousel-caption">
        <h3><%=lista.get(2).getName()%></h3>
      </div>
    </div>

    <div class="item">
      <img src="<%=lista.get(3).getImgUrl() %>"  alt="Flower">
      <div class="carousel-caption">
        <h3><%=lista.get(3).getName()%></h3>
      </div>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
                    </div>
    </br>
                    <div class="row">
        <% lista = (ArrayList<Grad>)request.getAttribute("gradovi");
        for(Grad item : lista)
        {
            
        %>
        
            <div class="panel panel-info col-md-3">
                <div class="panel-heading text-center">
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
        
            <% } %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
