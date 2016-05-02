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
             color: white; height:60vh;">

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
                                <th></th>
                            </tr>
                        </thead>

                        <tbody class="employeeData">
                            <c:forEach var="e" items="${currentStockHoldings}" >
                                <tr>
                                    <td class="accountNumber">${e.accountNumber}</td>
                                    <td class="stockSymbol">${e.stockSymbol}</td>
                                    <td class="totalShares">${e.totalShares}</td>
                                    <td>
                                        <button class="saveChanges btn btn-error" style="color:black" >Edit</button> 
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
                            <button class="btn btn-default " type="button" style="color:black;" id="gcoSubmit">Submit</button>
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


        <!-- Modal FOR Editing stock-->
        <div id="stockModal" class="modal fade" role="dialog">
            <div style="height: 70%; margin: 15vh auto; width: 70%;" class="modal-dialog">

                <!--Modal content-->
                <div class="modal-content" style="height: 100%; width: 100%">
                    <div stlye="width:100%; height:5%;" class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Stock: </h4>
                    </div>
                    <div stlye="width:100%; height:90%;" class="modal-body" style="height: 80%;">
                        <div >
                            <label for="stockSymbol">Stock Symbol:</label>
                            <h1 id="stockSymbol"></h1>
                            <label for="number-shares">Number of Shares Owned</label>
                            <p id="number-shares"></p>
                            <label for="accNum">Account Number</label>
                            <p id="acctNum"></p>
                        </div>
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            Sell Stock
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        Stuff goes here to sell stocks
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            Add Trailing Stop
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                    <div class="panel-body">
                                        trailing stop goes here 
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <h4 class="panel-title">
                                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                            Add Hidden Stops
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                    <div class="panel-body">
                                        stuff for hidden stop goes here 
                                    </div>
                                </div>
                            </div>
                        </div>                    </div>
                    <div stlye="width:100%; height:5%;" class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>



        <jsp:include page="footer.jsp"/>
    </body>
</html>
