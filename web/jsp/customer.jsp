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
                                <th>Current Price Per Share</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody class="employeeData">
                            <c:forEach var="e" items="${currentStockHoldings}" >
                                <tr>
                                    <td class="accountNumber">${e.accountNumber}</td>
                                    <td class="stockSymbol">${e.stockSymbol}</td>
                                    <td class="totalShares">${e.totalShares}</td>
                                    <td class="price">${e.currentPrice}</td>
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
                                    <td>${e.accountNum}</td>
                                    <td>${e.transId}</td>
                                    <td>${e.stockSymbol}</td>
                                    <td>${e.dat}</td>
                                    <td>${e.nos}</td>
                                    <td>${e.price}</td>
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
            <div  class="modal-dialog">

                <!--Modal content-->
                <div class="modal-content" >
                    <div  class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Stock: </h4>
                    </div>
                    <div  class="modal-body" style="height: 80%; overflow-y: scroll;">
                        <div >
                            <div  class="row">
                                <div class="col-sm-6">
                                    <label for="stockSymbol">Stock Symbol</label>
                                    <p class="stockSymbol"></p>
                                </div>
                                <div class="col-sm-6">
                                    <label for="currentPrice">Current Price Per Share</label> 
                                    <p id="currentPrice"></p>
                                </div>
                            </div>
                            <div  class="row">
                                <div class="col-sm-6">
                                    <label for="acctNum">Account Number</label>
                                    <p class="acctNum"></p>
                                </div>
                                <div class="col-sm-6">
                                    <label for="numShares">Number Shares Owned</label>
                                    <p id="numShares"></p>
                                </div>
                            </div>
                        </div>

                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs tabs-left"><!-- 'tabs-right' for right tabs -->
                            <li class="active"><a href="#sell" data-toggle="tab">Sell Stock</a></li>
                            <li><a href="#trailing" data-toggle="tab">Add Trailing Stop</a></li>
                            <li><a href="#hidden" data-toggle="tab">Add Hidden Stop</a></li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="sell">
                                <h2>Sell Stock</h2>                       
                                <h3 id="shares-lower" class="shares-bounds"> 0 </h3>
                                <input id="shares" type="range" name="shares"
                                       value="0" min="0" max="" data-highlight="true" data-show-value="true" />  
                                <h3 id="shares-upper" class="shares-bounds">  </h3>
                                <br><br><br>
                                <form action="/addSellOrder">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <label for="number-shares">Number of Shares:</label>
                                            <input id="number-shares" name="number-shares" class="form-control buy-text" type="text" value="0" disabled>
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="sellPrices">Total Sell Price:</label>
                                            <input id="sellPrices" name="sellPrice" class="form-control buy-text" type="text" value="0" disabled>
                                        </div>
                                        <div class="col-sm-4 btn-group">
                                            <label for="sellType">Sell Type:</label>
                                            <select id="sellType" name="sellType" class="form-control">
                                                <option value="Market">Market</option>
                                                <option value="Market-On-Close">Market-On-Close</option>
                                            </select>
                                        </div>
                                    </div>
                                    <input name="an" class="hidden acctNum"/>
                                    <input name="ss" class="hidden stockSymbol"/>
                                    <input id="sell-submit" name="submit" class="btn btn-default pull-right" value="Sell Shares" type="submit">
                                </form>
                            </div>

                            <div class="tab-pane" id="trailing">
                                <h2>Add Trailing Stop</h2>                       

                                <label for="trailingStop">Trailing Amount:</label>
                                <input id="trailingStop" name="number-shares" class="form-control" type="number" value="" min="0">
                                <input id="trailing-submit" name="submit" class="btn btn-default pull-right" value="Add Trailing" type="submit">
                            </div>
                            <div class="tab-pane" id="hidden">                                
                                <h2>Add Hidden Stop</h2>                       

                                <label for="hiddenStop">Hidden Stop Percentage:</label>
                                <input id="hiddenStop" name="number-shares" class="form-control" type="number" value="" min="0">
                                <input id="hidden-submit" name="submit" class="btn btn-default pull-right" value="Add Hidden" type="submit">
                            </div>
                        </div>




                    </div>
                    <div  class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>



        <jsp:include page="footer.jsp"/>
    </body>
</html>
