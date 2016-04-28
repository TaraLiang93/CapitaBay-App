<jsp:include page="header.jsp">
    <jsp:param name="title" value="${name} home"/>
    <jsp:param name="css" value="/css/employee.css" />
    <jsp:param name="css" value="/css/signupPage.css"/>
    <jsp:param name="js" value="/js/employee.js"/>
</jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="display-content employeeTabs col-md-12">
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
    <li role="presentation" class=""><a href="#staff" aria-controls="staff" role="tab" data-toggle="tab">Edit Staff</a></li>
    <li role="presentation" class=""><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Orders</a></li>
    <li role="presentation" class=""><a href="#customrs" aria-controls="customers" role="tab" data-toggle="tab">Edit Customers</a></li>
    <li role="presentation" class=""><a href="#stock" aria-controls="stock" role="tab" data-toggle="tab">Stock</a></li>
</ul>

 <div class="tab-content">
     <div role="tabpanel" class="tab-pane " id="home">
         Home page should display what it needs too
     </div>
     <div role="tabpanel" class="tab-pane active" id="staff">
         <table class = "table">
   <caption>Edit Employees</caption>
   <thead>
      <tr>
         <th>Lay Off</th>
         <th>First Name</th>
         <th>Last Name</th>
         <th>Address</th>
         <th>Telephone</th>
         <th>Zip Code</th>
         <th>Position</th>
         <th>Hour Rate</th>
         <th>Save</th>
      </tr>
   </thead>
   
   <tbody>
    <c:forEach var="e" items="${employees}" >
      <tr>
          <td><button class="btn btn-danger deleteEmployee">
                  <i class="glyphicon glyphicon-remove"></i></button>
              <div class="EmployeeId" style="display:none">${e.socialSecurityNumber}</div>
          </td>
         <td>${e.firstName}</td>
         <td>${e.lastName}</td>
         <td>${e.address}</td>
         <td>${e.telephone}</td>
         <td>${e.zipCode}</td>
         <td>
             <select class="employeePos" value="${e.position}">
                 <option name="Manager" value="Manager" ${e.position eq "Manager" ? "selected" : ""} >Manager</option>
                 <option name="CustomerRep" value="CustomerRep" ${e.position eq "CustomerRep" ? "selected" : ""}>Customer Rep</option>
             </select
         </td>
         <td>
             <div class="btn-inline">
                $
                <div class="btn-group">
                   <input class="col-md-8 input" type="text" name="hourRate" value="${e.hourlyRate}"/>
                </div>
             </div>
         </td>
         <td>
             <a class="saveChanges btn btn-primary">Save</a> 
         </td>
      </tr>
    </c:forEach>
   </tbody>
	
</table>
         <div class="employeeButtons pull-right">
            <a class="btn btn-info" id="addEmployee" data-toggle="modal" data-target="#addEmployeeModal">Add Employees</a>
         </div>
     </div>
     
     
     <div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Give Jobs</h4>
      </div>
      <div class="modal-body">
          <div id="signup">
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
                <div class="btn-group">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary ">Hire</button>
                </div>
            </form> 
          </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
     
     
     <div role="tabpanel" class="tab-pane" id="orders">
         Jesus
     </div>
     <div role="tabpanel" class="tab-pane" id="customrs">
         Love
     </div>
     <div role="tabpanel" class="tab-pane" id="stock">
         God
     </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>