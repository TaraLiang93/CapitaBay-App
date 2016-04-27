<%-- 
    Document   : signupPage
    Created on : Apr 26, 2016, 1:48:57 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="Sign Up"/>
            <jsp:param name="css" value="/css/signupPage.css"/>
            <jsp:param name="js" value="" />
        </jsp:include>
    </head>
    <body>
        <div style="width: 100%;height: 80%;margin-top: 20%;">
            <form>
              <div class="form-group">
                <label for="Username">Username</label>
                <input type="text" class="form-control" id="Username" placeholder="Username">
              </div>
              <div class="form-group">
                <label for="Password">Password</label>
                <input type="password" class="form-control" id="Password" placeholder="Password">
              </div>
              <div class="form-group">
                <label for="exampleInputFile">File input</label>
                <input type="file" id="exampleInputFile">
                <p class="help-block">Example block-level help text here.</p>
              </div>
              <div class="form-group">
                  <label for="FirstName">First Name</label>
                  <input type="text" class="form-control" id="firstName" placeholder="First Name">
              </div>
              
              <button type="submit" class="btn btn-default">Submit</button>
            </form>        
        </div>
    </body>
</html>
