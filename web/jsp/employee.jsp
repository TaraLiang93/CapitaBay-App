<jsp:include page="header.jsp">
    <jsp:param name="title" value="${name} home"/>
    <jsp:param name="css" value="/css/employee.css" />
    <jsp:param name="css" value="/css/signupPage.css"/>
    <jsp:param name="js" value="/js/employee.js"/>
</jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="display-content employeeTabs col-md-12">
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Main Page</a></li>
    <li role="presentation" class=""><a href="#staff" aria-controls="staff" role="tab" data-toggle="tab">Staff</a></li>
    <li role="presentation" class=""><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Orders</a></li>
    <li role="presentation" class=""><a href="#customrs" aria-controls="customers" role="tab" data-toggle="tab">Customers</a></li>
    <li role="presentation" class=""><a href="#stock" aria-controls="stock" role="tab" data-toggle="tab">Stock</a></li>
</ul>

 <div class="tab-content">
     <div role="tabpanel" class="tab-pane active" id="home">
         <div class="revenueTables col-xs-12">
             <h3>Revenue of Stock, Stock type and Customer</h3>
            <div class="form-group col-xs-8">
                <input type="text" class="form-control " id="searchRevenue" placeholder="Search">
            </div>
            <div class="form-group col-xs-3">
                <select class="searchRev form-control">
                    <option name="Stock" value="Stock">Stock</option>
                    <option name="StockType" value="StockType">Stock Type</option>
                    <option name="Customer" value="Customer">Customer</option>
                </select>
            </div>
            <a class="btn btn-sm btn-default searchRevBtn col-xs-1">Search</a>
         </div>
         
         <table class="table table-condensed table-bordered revTable" id="revStockTable" style="display: none"></table>
         <table class="table table-condensed table-bordered revTable" id="revStockTypeTable" style="display: none"></table>
         <table class="table table-condensed table-bordered revTable" id="revCustomerTable" style="display: none"></table>
     
        <div class="col-xs-12 salesReport">
            <h3>Monthly Sales Report</h3>
            <div class="col-xs-3">
                <select name="salesReportMonth" value="Select Month" class="salesReportMonth form-control">
                    <option>Select A Month</option>
                    <option name="January" value="1">January</option>
                    <option name="February" value="2">February</option>
                    <option name="March" value="3">March</option>
                    <option name="April" value="4">April</option>
                    <option name="May" value="5">May</option>
                    <option name="June" value="6">June</option>
                    <option name="July" value="7">July</option>
                    <option name="August" value="8">August</option>
                    <option name="September" value="9">September</option>
                    <option name="October" value="10">October</option>
                    <option name="November" value="11">November</option>
                    <option name="December" value="12">December</option>
                </select>
            </div>
        </div>
     </div>
     <div role="tabpanel" class="tab-pane " id="staff">
         <table class = "table editEmployeeTable">
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
   
   <tbody class="employeeData">
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
             <select class="employeePos form-control" value="${e.position}">
                 <option name="Manager" value="Manager" ${e.position eq "Manager" ? "selected" : ""} >Manager</option>
                 <option name="CustomerRep" value="CustomerRep" ${e.position eq "CustomerRep" ? "selected" : ""}>Customer Rep</option>
             </select>
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
    
         
    <a class="btn btn-info richestRep">Rep of the Month</a>
     <div class="richestRepInput">

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
            <form  id="newEmployee">
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
                 <select class="employeePos" name="posistion" id="newPosition">
                 <option name="Manager" value="Manager">Manager</option>
                 <option name="CustomerRep" value="CustomerRep">Customer Rep</option>
             </select>
                 <div class="form-group">
                     <label for="newHourlyRate">New Salary</label>
                     <input type="number" class="form-control" name="newHourlyRate" id="newHourlyRate" placeholder="25" />
                 </div>
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
              <button  class="btn btn-primary hireEmployee">Hire</button>
                </div>
            </form> 
          </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
     
     
     <div role="tabpanel" class="tab-pane" id="orders">

         <div class="input-group col-md-10" style="margin: 0 auto">
            <h3>List Orders by Customer Name or Stock symbol</h3>
             <div class="form-group col-md-8">
                <input type="text" class="form-control " id="orderSearch" placeholder="Search">
             </div>
            <div class="form-group col-md-3">
                <select class="search form-control">
                   <option name="customerName" value="customerName">Customer</option>
                   <option name="stockSymbol" value="stockSymbol">Stock Symbol</option>
                </select>
            </div>
            <a class="btn btn-sm btn-default searchOrders col-md-1">Search</a>

             <table class="table table-hover searchOrderTable col-md-12">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Type</th>
                        <th>Customer SSN</th>
                        <th>Employee SSN</th>
                        <th>Stock Symbol</th>
                        <th>Account Number</th>
                        <th>Order Date</th>
                        <th>Order Time</th>
                        <th>Shares Brought</th>
                        <th>Share Price</th>  
                    </tr>
                </thead>
                <tbody id="orderSerchResults">
                    
                </tbody>
             </table>
         </div>
     </div>
     <div role="tabpanel" class="tab-pane" id="customrs">
         Love
     </div>
     <div role="tabpanel" class="tab-pane" id="stock">
   <table class = "table allStocksTable table-condensed ">
   <caption>All Stocks</caption>
   <thead>
      <tr>
          <td>Stock symbol</td>
           <td>Stock type</td>
           <td>Stock name</td>
           <td>Share Price</td>
           <td>Stock Date</td>
           <td>Shares Avaliable</td>
           <td>Update</td>
      </tr>
   </thead>
   
   <tbody class="employeeData">
    <c:forEach var="s" items="${listAllStocks}" >
      <tr>
          <td class="stockSymbol">${s.stockSymbol}</td>
          <td>${s.stockType}</td>
          <td>${s.stockName}</td>
          <td><input type="number" class="sharePrice" value="${s.sharePrice}"/></td>
          <td>${s.stockDate}</td>
          <td>${s.numberOfSharesAvaliable}</td>
          <td><button class="btn btn-info updateStock">Update</button>
      </tr>
    </c:forEach>
   </tbody>
	
</table>
         
   <table class = "table popStocksTable table-condensed table-bordered">
   <caption>Most Traded Stocks</caption>
   <thead>
      <tr>
          <td>Stock symbol</td>
           <td>Stock name</td>
           <td>Share Price</td>
           <td>Shares Avaliable</td>
      </tr>
   </thead>
   
   <tbody class="popularStocks">
    <c:forEach var="s" items="${popularStocks}" >
      <tr>
          <td class="stockSymbol">${s.stockSymbol}</td>
          <td>${s.stockName}</td>
          <td>${s.sharePrice}</td>
          <td>${s.numberOfSharesAvaliable}</td>
      </tr>
    </c:forEach>
   </tbody>
	
</table>
         
     </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>