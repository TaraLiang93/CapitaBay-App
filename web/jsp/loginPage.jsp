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
    <jsp:param name="css" value="/css/loginCss.css" />
    <jsp:param name="js" value="" />
</jsp:include>
    </head>
    <body>
        <div clas="container">
            <div class="jumbotron" id="loginDiv">
                <p>Email: </p>
                <input type="text">
            </div>
        </div>
    </body>
</html>
