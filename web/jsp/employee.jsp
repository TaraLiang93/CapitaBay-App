<jsp:include page="header.jsp">
    <jsp:param name="title" value="${name} home"/>
    <jsp:param name="css" value="/css/employee.css" />
    <jsp:param name="js" value="/js/employee.js"/>
</jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="display-content employeeTabs col-md-12">
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
    <li role="presentation"><a href="#staff" aria-controls="staff" role="tab" data-toggle="tab">Edit Staff</a></li>
    <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Orders</a></li>
    <li role="presentation"><a href="#customrs" aria-controls="customers" role="tab" data-toggle="tab">Edit Customers</a></li>
    <li role="presentation"><a href="#stock" aria-controls="stock" role="tab" data-toggle="tab">Stock</a></li>
</ul>

 <div class="tab-content">
     <div role="tabpanel" class="tab-pane active" id="home">
         Home page should display what it needs too
     </div>
     <div role="tabpanel" class="tab-pane" id="staff">
         <table class = "table">
   <caption>Edit Employees</caption>
   <thead>
      <tr>
         <th>Remove</th>
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
          <td><button class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></button></td>
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
            <a class="btn btn-info" id="addEmployee">Add Employees</a>
         </div>
     </div>
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