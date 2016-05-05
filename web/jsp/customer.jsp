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
             color: white; height:60vh; overflow-y: scroll;">

            <h1>${userBean.username }'s Profile</h1>       

            <!--                 Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                <li role="presentation"><a href="#stockHolding" aria-controls="stockHolding" role="tab" data-toggle="tab">Current Stock Holding</a></li>
                <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Order History</a></li>
                <li role="presentation"><a href="#sugesstions" aria-controls="sugesstions" role="tab" data-toggle="tab">Stock Suggestion</a></li>
                <li role="presentation"><a href="#conditionalOrder" aria-controls="conditionalOrder" role="tab" data-toggle="tab">Conditional Order History</a></li>


                <div role="tabpanel" class="tab-pane active" id="home">
                    <form action="/logOutServlet" method="GET">
                        <button class="btn btn-error pull-right" style="color: red; margin: 1%;" type="submit">Log Out</button>
                    </form>
                </div>
            </ul>

            <!--                 Tab panes -->
            <div class="tab-content">



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
                                <th>Order ID</th>
                                <th>Percentage</th>
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
                                    <label for="numShares">Number Shares Owned</label>
                                    <p id="numShares"></p>
                                </div>
                                <div class="col-sm-6">
                                    <label for="currentPrice">Current Price Per Share</label> 
                                    <p id="currentPrice"></p>
                                </div>
                            </div>
                        </div>

                        <form action="/addSellOrder" method="post">           
                            <h3 id="shares-lower" class="shares-bounds"> 0 </h3>
                            <input id="shares" type="range" name="shares"
                                   value="0" min="0" max="" data-highlight="true" data-show-value="true" />  
                            <h3 id="shares-upper" class="shares-bounds">  </h3>
                            <br><br><br>
                            <div  class="row">
                                <div class="col-sm-4">
                                    <label for="ss">Stock Symbol</label>
                                    <input type="text" name="ss" id="ss" class="stockSymbol" readonly/>
                                </div>
                                <div class="col-sm-4">
                                    <label for="an">Account Number</label>
                                    <input type="text" name="an" id="an" class="acctNum" readonly/>
                                </div>
                                <div class="col-sm-4">
                                    <label for="shareNums">Number of Shares:</label>
                                    <input id="shareNums" name="shareNums" class="form-control buy-text" type="text" value="0" readonly />
                                </div>
                            </div>

                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs tabs-left"><!-- 'tabs-right' for right tabs -->
                                <li class="active" ><a href="#sell" data-toggle="tab" class="tab">Sell Stock</a></li>
                                <li><a href="#trailing" data-toggle="tab" class="tab">Add Trailing Stop</a></li>
                                <li><a href="#hidden" data-toggle="tab" class="tab">Add Hidden Stop</a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane active" id="sell">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <label for="sellPrices">Total Sell Price:</label>
                                            <input id="sellPrices" name="sellPrice" class="form-control buy-text" type="text" value="0" readonly />
                                        </div>
                                        <div class="col-sm-6 btn-group">
                                            <label for="sellType">Sell Type:</label>
                                            <select id="sellType" name="sellType" class="form-control">
                                                <option name='Market' value="Market">Market</option>
                                                <option name='Market-On-Close' value="Market-On-Close">Market-On-Close</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="hidden">
                                    <div class="col-sm-10">
                                        <label for="an">Hidden Stop Price:</label>
                                        <input type="number" name="hiddenStopPrice" id="an" class="form-control" value="-1" step="any" />
                                    </div>
                                    <input type="text" name="hiddenType" style="display:none;" value="hidden"/>

                                </div>
                                <div class="tab-pane" id="trailing">                                
                                    <div>
                                        <label for="trailingStop">Trailing Stop Percentage(Minimum:0 - Maximum:100):</label>
                                        <input id="trailingStop" name="trailPercent" class="form-control" type="number" value="-1" step="any"/>
                                    </div>
                                    <input type="text" name="trailType" style="display:none;" value="trailing"/>

                                </div>
                            </div>
                            <br>
                            <input  name="submit" class="btn btn-default pull-right" value="Submit" type="submit">
                        </form>




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
