<%-- 
    Document   : customer
    Created on : Apr 28, 2016, 5:41:51 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${name} home"/>
            <jsp:param name="css" value="/css/customer.css" />
            <jsp:param name="js" value="/js/customer.js"/>
        </jsp:include>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    </head>
    <body>


        <div class="display-content customerTabs col-md-12" 
             style="background-color: rgba(0,0,0,0.7); border-radius: 15px;
             color: white; height:80vh;">

            <h1>${userBean.username }'s Profile</h1>       

            <!--                 Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                <li role="presentation"><a href="#stockHolding" aria-controls="stockHolding" role="tab" data-toggle="tab">Current Stock Holding</a></li>
                <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Order History</a></li>
                <li role="presentation"><a href="#sugesstions" aria-controls="sugesstions" role="tab" data-toggle="tab">Stock Suggestion</a></li>
                <li role="presentation"><a href="#conditionalOrder" aria-controls="conditionalOrder" role="tab" data-toggle="tab">Conditional Order History</a></li>


            </ul>

            <!--                 Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <form action="/logOutServlet" method="GET">
                        <button class="btn btn-default" type="submit">Log Out</button>
                    </form>
                </div>


                <!--//current stock Holding-->
                <div role="tabpanel" class="tab-pane" id="stockHolding">
                    <table class = "table">
                        <thead>
                            <tr>
                                <th>Account Number</th>
                                <th>Stock Symbol</th>
                                <th>Number of Share Owned</th>
                                <th>Sell</th>
                            </tr>
                        </thead>

                        <tbody class="employeeData">
                            <c:forEach var="e" items="${currentStockHoldings}" >
                                <tr>
                                    <td>${e.accountNumber}</td>
                                    <td>${e.stockSymbol}</td>
                                    <td>${e.totalShares}</td>
                                    <td>
                                        <a class="saveChanges btn btn-error">Sell</a> 
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>


                <!--//Past order-->
                <div role="tabpanel" class="tab-pane" id="orders">
                    <table class = "table">
                        <thead>
                            <tr>
                                <th>Account Number</th>
                                <th>Order ID</th>
                                <th>Order Type</th>
                                <th>Stock Brought</th>
                                <th>Order Date/Time</th>
                                <th>Number of Share Brought</th>
                                <th>Share Price</th>
                                <th>Stock Broker</th>
                            </tr>
                        </thead>
                        <tbody class="employeeData">
                            <c:forEach var="e" items="${orderHistory}" varStatus="test">
                                <tr>
                                    <td>${e.accountNumber}</td>
                                    <td>${e.orderID}</td>
                                    <td>${e.orderType}</td>
                                    <td>${e.stockSymbol}</td>
                                    <td>${e.orderDate}/${e.orderTime}</td>
                                    <td>${e.numberOfShares}</td>
                                    <td>${e.sharePrice}</td>
                                    <td>${e.employeeSSN}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>                    
                </div>


                <!--Get Stock Suggestions-->
                <div role="tabpanel" class="tab-pane" id="sugesstions">
                    <table class = "table">
                        <thead>
                            <tr>
                                <th>Stock Symbol</th>
                                <th>Stock Name</th>
                                <th>Share Price</th>
                                <th>Number of Share Avaliable</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="e" items="${stockSuggestion}" varStatus="test">
                                <tr>
                                    <td>${e.stockSymbol}</td>
                                    <td>${e.stockName}</td>
                                    <td>${e.sharePrice}</td>
                                    <td>${e.numberOfSharesAvaliable}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>



                <div role="tabpanel" class="tab-pane" id="conditionalOrder" style="margin-top: 2.5vh;">
                    <p class="failMessage"></p>
                    <div class="row">
                            <div class="col-sm-8">
                                <p class="col-sm-2">Order ID:</p>
                                <input type="text" class="col-sm-10" id="orderID" style="color:black;"/>
                            </div>
                            <div class="form-group col-sm-3">
                                <select class="form-control" id="conditionalType">
                                    <option>Trailing Stop</option>
                                    <option>Hidden Stop</option>
                                </select>
                            </div>
                            <div class="col-sm-1">
                                <button class="btn btn-default coSubmit" type="button" style="color:black;" id-="coSubmit">Submit</button>
                            </div>
                    </div>
                    <table class = "table">
                        <thead>
                            <tr>
                                <th>Share Price</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody id="condHistory">
                           
                        </tbody>
                    </table>
                </div>
            </div>





        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
