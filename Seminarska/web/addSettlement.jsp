<%-- 
    Document   : addCity
    Created on : Sep 1, 2015, 12:43:08 AM
    Author     : martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row col-md-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <strong > Add new settlement</strong>
                    </div>
                    <div class="panel-body">
                        <form action="" method="post">
                            <div class="form-group">
                                <label>Settlement name</label> 
                                <input type="text" class="form-control" name="name" id="name"/>
                            </div>
                            <div>
                                <label>Population</label> 
                                <input type="number" class="form-control" name="population" id="population"/>
                            </div>

                            <div>
                                <label>Abstract</label> <input type="text" class="form-control"
                                                               name="abstract" id="abstract" />
                            </div>

                            <div>
                                <label>Leader</label> <input type="text" class="form-control"
                                                             name="leader" id="leader"/>
                            </div>
                            <div class="form-group">
                                <label>Upload image</label> 
                                <input type="file" class="btn btn-primary" ng-file-select="onFileSelect($files)" multiple accept="image/*">
                            </div>
                            <div class="form-group"> 
                                <input type="submit" class="btn btn-primary" value="Add settlement">
                                <a type="submit" class="btn btn-danger pull-right" href="/Seminarska/index">Cancel</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
