<jsp:include page="header.jsp">
    <jsp:param name="title" value="${userBean.firstName} home"/>
    <jsp:param name="css" value="/css/employee.css" />
    <jsp:param name="css" value="/css/signupPage.css"/>
    <jsp:param name="js" value="/js/employee.js"/>
</jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/CAPITABAY?zeroDateTimeBehavior=convertToNull"  user="root"  password="${PASSWD}"/>

<div class="display-content employeeTabs col-md-12">
    <h1>${userBean.username }'s Profile</h1>    
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Main Page</a></li>
    <li role="presentation" class=""><a href="#staff" aria-controls="staff" role="tab" data-toggle="tab">Staff</a></li>
    <li role="presentation" class=""><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Orders</a></li>
    <li role="presentation" class=""><a href="#customrs" aria-controls="customers" role="tab" data-toggle="tab">Customers</a></li>
    <li role="presentation" class=""><a href="#stock" aria-controls="stock" role="tab" data-toggle="tab">Stock</a></li>
    <c:if test="${userBean.status eq 'Manager'}">
    <li role="presentation"><a href="#addStock" aria-controls="addStock" role="tab" data-toggle="tab">Add Stock</a></li>
    </c:if>
    <button class="btn btn-error pull-right logoutEmployee" style="color: red;">Log Out</button>
</ul>

    
    <c:if test="${userBean.status == 'Manager'}">
        <!--<script>alert("${userBean.status}");</script>-->
    </c:if>
    
    
 <div class="tab-content">
     <div role="tabpanel" class="tab-pane active" id="home">
         
        <c:if test="${userBean.status == 'Manager'}">
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
        </c:if>
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
         <c:if test="${userBean.status == 'Manager'}"><th>Hour Rate</th>
             <th>Save</th></c:if>
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
             <c:choose>
                 <c:when test="${userBean.status == 'Manager'}">
                    <select class="employeePos form-control" value="${e.position}">
                    <option name="Manager" value="Manager" ${e.position eq "Manager" ? "selected" : ""} >Manager</option>
                    <option name="CustomerRep" value="CustomerRep" ${e.position eq "CustomerRep" ? "selected" : ""}>Customer Rep</option>
                   </select>
                 </c:when>
                 <c:when test="${userBean.status == 'CustomerRep'}">
                     ${e.position}
                 </c:when>
                 <c:otherwise>
                     this should never print
                 </c:otherwise>
             </c:choose>
         </td>
         <c:if test="${userBean.status == 'Manager'}"><td>
             <div class="btn-inline">
                $
                <div class="btn-group">
                   <input class="col-md-8 input form-control" type="text" name="hourRate" value="${e.hourlyRate}"/>
                </div>
             </div>
         </td>
         <td>
             <a class="saveChanges btn btn-primary">Save</a> 
         </td></c:if>
      </tr>
    </c:forEach>
   </tbody>
	
</table>
         <div class="employeeButtons pull-right">
            <a class="btn btn-info" id="addEmployee" data-toggle="modal" data-target="#addEmployeeModal">Add Employees</a>
         </div>
    
         
    <a class="btn btn-info richestRep">Rep of the Month</a>
     <div class="richestRepInput"></div>
     
     </div>
     

     
<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Give Jobs</h4>
      </div>
      <div class="modal-body">
          <form class="asyncForm" id="signup">
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
                 <select class="employeePos" name="position" id="newPosition">
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
              <button  class="btn btn-primary hireEmployee" id="newEmployee">Hire</button>
                </div>
          </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
     
     
     <div role="tabpanel" class="tab-pane" id="orders">

        <c:if test="${userBean.status == 'Manager'}">
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
        </c:if>
         
         <c:if test="${userBean.status eq 'CustomerRep'}" >
             <h3 class="text-center">${userBean.username}'s Orders Receipt</h3>
             
             
             <sql:query dataSource="${snapshot}" var="repOrders" sql="SELECT O.* FROM Orders O INNER JOIN Employee E ON O.EmployeeSSN = E.SocialSecurityNumber WHERE O.EmployeeSSN = ${userBean.socialSecurityNumber}" />
             
             <table class="table table-bordered repOrders">
                 <thead>
                     <tr>
                        <th>Order ID</th>
                        <th>Order Type</th>
                        <th>Customer SSN</th>
                        <th>Employee SSN</th>
                        <th>Account Number</th>
                        <th>Order Date</th>
                        <th>Order Time</th>
                        <th>Shares Brought</th>
                        <th>Share Price</th>  
                     </tr>
                 </thead>
                 <tbody>
                     <c:forEach var="repOrder" items="${repOrders.rows}">
                         <tr>
                             <td>${repOrder.OrderID}</td>
                             <td>${repOrder.OrderType}</td>
                             <td>${repOrder.SocialSecurityNumber}</td>
                             <td>${repOrder.EmployeeSSN}</td>
                             <td>${repOrder.AccountNumber}</td>
                             <td>${repOrder.OrderDate}</td>
                             <td>${repOrder.OrderTime}</td>
                             <td>${repOrder.NumberOfShares}</td>
                             <td>${repOrder.SharePrice}</td>
                         </tr>
                     </c:forEach>
                 </tbody>
             </table>
         </c:if>
     </div>
     <div role="tabpanel" class="tab-pane" id="customrs">
    <c:if test="${userBean.status == 'Manager'}">
         <h3 class="text-center">Richest Customer</h3>
         
         <table class="table table-bordered">
             <thead>
                 <tr>
                     <th>Customer ID</th>
                     <th>First Name</th>
                     <th>Last Name</th>
                     <th>Revenue</th>
                 </tr>
             </thead>
             <tbody>
                <tr>
                    <td>${richestCustomer.ssn}</td>
                    <td>${richestCustomer.FName}</td>
                    <td>${richestCustomer.LName}</td>
                    <td>${richestCustomer.revenue}</td>
                    
                </tr>
             </tbody>
         </table>
    
         <div class="jumbotron">
            <h3>${richestCustomer.FName} has amass a wealth of ${richestCustomer.revenue} and they will be ruler of the galaxy and the new
            world is there's.
            </h3>
         </div>
    </c:if>
            
            <table class="table table-bordered customerTable">
                <caption>All Customers</caption>
                <thead>
                    <tr>
                        <th>revoke</th>
                        <th>ID</th>
                        <th>Rating</th>
                        <th>Name</th>
                        <th>Credit #</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Zip</th>
                        <th>Phone #</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${customers}">
                    <tr>
                        <td>
                            <a class="btn btn-danger removeCustomer">
                                <i class="glyphicon glyphicon-remove"></i>
                            </a>
                        </td>
                        <td class="customerID">${c.socialSecurityNumber}</td>
                        <td><input type="text" class="customerRating form-control" value="${c.rating}" /></td>
                        <td>${c.firstName} ${c.lastName}</td>
                        <td><input type="text" class="form-control customerCredit" value="${c.creditCardNumber}"/></td>
                        <td><input type="text" class="form-control customerEmail" value="${c.email}" /></td>
                        <td>${c.address}</td>
                        <td>${c.zipCode}</td>
                        <td>${c.telephone}</td>
                        <td><a class="btn btn-primary updateCustomers"><i class="glyphicon glyphicon-floppy-saved" aria-hidden="true"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-info" class="addCustomer" data-toggle="modal" data-target="#addCustomerModal">Add Customer</a>

            <c:if test="${userBean.status != 'Customer'}">
                
            
            
            <sql:query dataSource="${snapshot}" var="mailingList" sql="SELECT P.SocialSecurityNumber, P.Username, P.FirstName, P.LastName, C.Email FROM Person P INNER JOIN Customer C ON P.SocialSecurityNumber = C.SocialSecurityNumber ORDER BY SocialSecurityNumber ASC;" />
            
            <div class="col-sm-12">
                <table class="table table-bordered mailingList">
                    <caption>Customer Mailing List</caption>
                    <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>UserName</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ex" items="${mailingList.rows}">
                            <tr>
                                <td>${ex.SocialSecurityNumber}</td>
                                <td>${ex.Username}</td>
                                <td>${ex.FirstName}</td>
                                <td>${ex.LastName}</td>
                                <td>${ex.Email}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            </c:if>
            
<div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Create Account</h4>
      </div>
      <div class="modal-body">
          <form class="asyncForm" id="addCustomerForm">
              <div class="form-group hasHalf">
                  <div class="half"> 
                    <label for="Username">Username</label>
                    <input type="text" class="form-control" id="cUsername" name="username" placeholder="Username">
                  </div>
                  <div class="half">
                        <label for="Email">Email</label>
                        <input type="Email" class="form-control" name="email" id="cEmail" placeholder="Email">
                  </div>
              </div>
              <div class="form-group hasHalf">
                  <div class="half">
                       <label for="Password">Password</label>
                       <input type="password" class="form-control" id="cPassword" name="password" placeholder="Password">
                  </div>
                  <div class="half">
                        <label for="Password2">Re-enter Password</label>
                        <input type="password" class="form-control" name="password2" id="cPassword2" placeholder="Password">
                  </div>
              </div>
              <div class="form-group hasHalf">
                  <div class="half">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" name="firstName" id="cfirstName" placeholder="First Name">
                  </div>
                  <div class="half">
                    <label for="LastName">Last Name</label>
                    <input type="text" class="form-control" name="lastName" id="cLastName" placeholder="Last Name">
                  </div>
              </div>
              <div class="form-group" style="width: 33%;">
                    <label for="phoneNum">Telephone</label>
                    <input type="number" class="form-control" name="phoneNum" id="cphoneNum" placeholder="Telephone Number">
              </div>
              <div class="form-group">
                  <label for="SSN">Social Security Number</label>
                  <input type="number" class="form-control" name="ssn" id="cSSN" placeholder="Social Security Number">
              </div>
              <div class="form-group">
                  <label for="creditCard">Credit Card Number</label>
                  <input type="number" class="form-control" name="creditCard" id="ccreditCard" placeholder="Credit Card Number">
              </div>
              <div class="form-group">
                  <div style="width: 90%;">
                    <label for="address">Address</label>
                    <input type="text" name="address" id="caddress" class="form-control" placeholder="Address">
                  </div>
                  <br>
                  <div style="display: inline-flex; width: 100%;" >
                      <div class="form-group" style="width: 30%;margin: 0.5%;" >
                        <label for="city">City</label>
                        <input type="text" class="form-control" name="city" id="ccity" placeholder="City">
                      </div>
                      <div class="form-group" style="width: 30%;margin: 0.5%;"> 
                            <label for="state">State</label>
                            <input type="text" class="form-control" name="state" id="cstate" placeholder="State">
                      </div>
                      <div class="form-group" style="width: 30%;margin: 0.5%;">
                        <label for="zip">Zip Code</label>
                        <input type="number" class="form-control" name="zip" id="czip" placeholder="Zip Code">
                      </div>
                  </div>
              </div>
              <button class="btn btn-primary addUserButton">Add Account</button>
            </form> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
            
     </div>

<div role="tabpanel" class="tab-pane" id="stock">
    <c:if test="${userBean.status == 'Manager'}">
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
                        <td><input type="number" class="sharePrice form-control" value="${s.sharePrice}"/></td>
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



    </c:if> 
    <h4>Click for suggestions</h4>
    <div class="customerSuggestions col-xs-12">
        <c:forEach var="c" items="${customers}">
            <div class="btn btn-default customerSugestion col-xs-3" >
                <p>${c.firstName} ${c.lastName}</p>
                <div class="customerSugID" style="display: none">${c.socialSecurityNumber}</div>
            </div>
        </c:forEach>
    </div>
    <table class="table table-hover table-condensed custSuggestList" style="display: none">
        
    </table>
</div>

    <div role="tabpanel" class="tab-pane" id="addStock">
                <form action="/addStock" method="post">
                    <div class="form-group row">
                        <div class="col-sm-4"> 
                            <label for="stockSymbol">Stock Symbol</label>
                            <input type="text" class="form-control" id="stockSymbol" name="stockSymbol" placeholder="Stock Symbol">
                        </div>
                        <div class="col-sm-4">
                            <label for="stockName">Stock Name</label>
                            <input type="text" class="form-control" name="stockName" id="stockName" placeholder="Stock Name">
                        </div>
                        <div class="col-sm-4">
                            <label for="stockType">Stock Type</label>
                            <input type="text" class="form-control" id="stockType" name="stockType" placeholder="Stock Type">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <label for="price">Price Per Share</label>
                            <input type="number" class="form-control" name="price" id="price" placeholder="Price">
                        </div>
                        <div class="col-sm-6">
                            <label for="nos">Number of Share</label>
                            <input type="number" class="form-control" name="nos" id="nos" placeholder="Number of Share">
                        </div>
                    </div>
                    <br>
                    <button class="btn btn-error pull-right" type="submit" style="color:black;">Add Stock</button>
                </form>
            </div>



  </div>
</div>
<jsp:include page="footer.jsp"/>