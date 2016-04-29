<%-- 
    Document   : loginPage
    Created on : Apr 22, 2016, 9:30:01 PM
    Author     : root
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="Login"/>
            <jsp:param name="css" value=""/>
            <jsp:param name="js" value="/js/loginPage.js" />
        </jsp:include>
    </head>
    <body>
    </div>
        <div class="container">
            <div class="jumbotron" id="loginDiv" 
                 style="width: 100%;height: 80%;margin-top: 20%;
                 background-color: rgba(0,0,0,0.7); border-radius: 15px;
                 color: white;">
                
                <h1>Log In</h1>
                <c:chose>
                    <c:when test="${errorStatus == 'true'}">
                        <p style="color:red;">${error}</p>
                    </c:when>
                </c:chose>
                <form class="form-horizontal" id="loginForm" action="/loginValidation" method="post">
                    <div class="form-group">
                      <label for="username" class="col-sm-2 control-label">Username</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Email">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                      <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
                      </div>
                    </div>
                    <div class="form-group pull-right">
                      <div class="col-sm-offset-2 col-sm-10">
                        <a href="/jsp/signupPage.jsp" style="color: white;">New User, Sign up.</a>
                        <button type="submit" class="btn btn-default">Sign in</button>
                      </div>
                    </div>
                  </form>
            </div>
        </div>
                    <jsp:include page="footer.jsp"/>
    </body>
</html>
