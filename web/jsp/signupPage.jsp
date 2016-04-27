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
        <div id="signup">
            <h1>Sign Up</h1>
            <c:chose>
                <c:when test="${errorStatus == 'true'}">
                    <p style="color:red;">${error}</p>
                </c:when>
            </c:chose>
                <form action="/newCustomer" method="post">
              <div class="form-group hasHalf">
                  <div class="half"> 
                    <label for="Username">Username</label>
                    <input type="text" class="form-control" id="Username" name="username" placeholder="Username">
                  </div>
                  <div class="half">
                        <label for="Email">Email</label>
                        <input type="Email" class="form-control" name="email" id="Email" placeholder="Email">
                  </div>
              </div>
              <div class="form-group hasHalf">
                  <div class="half">
                       <label for="Password">Password</label>
                       <input type="password" class="form-control" id="Password" name="password" placeholder="Password">
                  </div>
                  <div class="half">
                        <label for="Password2">Re-enter Password</label>
                        <input type="password" class="form-control" name="password2" id="Password2" placeholder="Password">
                  </div>
              </div>
              <div class="form-group hasHalf">
                  <div class="half">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name">
                  </div>
                  <div class="half">
                    <label for="LastName">Last Name</label>
                    <input type="text" class="form-control" name="lastName" id="LastName" placeholder="Last Name">
                  </div>
              </div>
              <div class="form-group" style="width: 33%;">
                    <label for="phoneNum">Telephone</label>
                    <input type="number" class="form-control" name="phoneNum" id="phoneNum" placeholder="Telephone Number">
              </div>
              <div class="form-group">
                  <label for="SSN">Social Security Number</label>
                  <input type="number" class="form-control" name="ssn" id="SSN" placeholder="Social Security Number">
              </div>
              <div class="form-group">
                  <label for="creditCard">Credit Card Number</label>
                  <input type="number" class="form-control" name="creditCard" id="creditCard" placeholder="Credit Card Number">
              </div>
              <div class="form-group">
                  <div style="width: 90%;">
                    <label for="address">Address</label>
                    <input type="text" name="address" id="address" class="form-control" placeholder="Address">
                  </div>
                  <br>
                  <div style="display: inline-flex; width: 100%;" >
                      <div class="form-group" style="width: 30%;margin: 0.5%;" >
                        <label for="city">City</label>
                        <input type="text" class="form-control" name="city" id="city" placeholder="City">
                      </div>
                      <div class="form-group" style="width: 30%;margin: 0.5%;"> 
                            <label for="state">State</label>
                            <input type="text" class="form-control" name="state" id="state" placeholder="State">
                      </div>
                      <div class="form-group" style="width: 30%;margin: 0.5%;">
                        <label for="zip">Zip Code</label>
                        <input type="number" class="form-control" name="zip" id="zip" placeholder="Zip Code">
                      </div>
                  </div>
              </div>
              <button type="submit" class="btn btn-default pull-right">Submit</button>
            </form>        
        </div>
    </body>
</html>
