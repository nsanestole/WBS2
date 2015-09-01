<%-- 
    Document   : login
    Created on : Aug 31, 2015, 6:38:40 PM
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
            <div class="panel panel-info text-center">
                <div class="panel-heading info">
                    <h3>Login</h3>
                </div>
                <div class="panel-body">
                    <form action="" method="post">
                        <div>
                            <label>Username:</label>

                            <input type="text" name="username" id="username"/>
                        </div>
                        <div>
                            <label>Password:</label>

                            <input type="password" name="password" id="password"/>
                        </div>
                        <div>
                            <div>
                                <input class="btn btn-success" type="submit" value="Login"/>
                                <a class="btn btn-danger" type="submit" href="/Seminarska/index">Cancel</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </body>
</html>
