<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/specificStock.js" />
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function(){
    $("#chart-overlay").hide();
    var count = 0;
    var chart = new CanvasJS.Chart("chartContainer", {
    backgroundColor: "#333",
            title:{
            text: ""
            },
            axisX:{
            labelFontColor: "#DDD"
            },
            axisY:{
            interlacedColor: "#555",
                    gridColor: "DDD",
                    labelFontColor: "#DDD"
            },
            data: [
            {

            type: "area",
                    color: "rgba(0,235,47,.7)",
                    showInLegend: true,
                    dataPoints: [
    <c:forEach var="his" items="${h}" varStatus="loopStatus">
                            
                    { label: "${his.stockDate}", y: ${his.sharePrice} }
                     <c:if test="${!loopStatus.last}">, </c:if>       
    </c:forEach>
        
                    ]
            }
            ]
    });
    chart.render();
    if(${count} == 1) {
        $("#chart-overlay").show();
    }
  
    
    $("[type=range]").change(function(){
    var newval = $(this).val();
    $("#number-shares").val(newval);
    $("#price-shares").val(parseFloat(Math.round(newval * ${s.sharePrice} * 100) / 100).toFixed(2));
    });
    $("#number-shares").change(function(){
    var newval = $(this).val();
    $("#shares").val(newval);
    $("#price-shares").val(parseFloat(Math.round(newval * ${s.sharePrice} * 100) / 100).toFixed(2));
    });
    $("#price-shares").change(function(){
    var price = $(this).val();
    var newval = Math.ceil(price / ${s.sharePrice});
    $("#shares").val(newval);
    $("#number-shares").val(newval);
    $("#price-shares").val(parseFloat(Math.round(newval * ${s.sharePrice} * 100) / 100).toFixed(2));
    });
    $("#chart-control input").blur(function() {
    $(location).attr("href", "/loadSpecificStockPage?val=${s.stockSymbol}&month=" + $("#months").val());
    });
    });
</script>

<div class="container">
    <div id="chart-container" class="content">

        <h2 id="specific-title">${s.stockName}</h2>
        <div class="row">
            <div id="buy-form" class="col-md-8">
                <c:if test="${userBean.status == 'customer'}">
                <form method="post" action="/buyServlet">
                    <h2> Buy this Stock </h2>                       
                    <h3 id="shares-lower" class="shares-bounds"> 0 </h3>
                    <input id="shares" type="range" name="shares"
                           value="0" min="0" max="${s.numberOfSharesAvaliable}" data-highlight="true" data-show-value="true" />  
                    <h3 id="shares-upper" class="shares-bounds"> ${s.numberOfSharesAvaliable} </h3>
                    <br><br><br>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="number-shares">Number of Shares:</label>
                            <input id="number-shares" name="number-shares" class="form-control buy-text" type="text" value="0">
                        </div>
                        <div class="col-md-6">
                            <label for="price-shares">Total Price:</label>
                            <input id="price-shares" name="price-shares" class="form-control buy-text" type="text" value="$0">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="stock-account">Stock Account:</label>
                            <select id="stock-account" name="stock-account" class="form-control">
                                <c:forEach var="act" items="${a}">
                                    <option value="${act.intValue()}">${act.intValue()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="order-type">Order Type:</label>
                            <select id="order-type" name="order-type" class="form-control">
                                <option value="market">Market</option>
                                <option value="marketOnClose">Market on Close</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label for="employee">Employee:</label>
                            <select id="employee" name="employee" class="form-control">

                                <c:forEach var="emp" items="${p}">
                                    <option value="${emp.socialSecurityNumber}">${emp.firstName} ${emp.lastName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <input name="stockSymbol" id="stockSymbol" value="${s.stockSymbol}" style="display: none">
                    <input id="buy-submit" name="submit" class="btn btn-success" value="Buy Shares" type="submit">

                </form>
                    </c:if>
            </div>
            <div id="specific-table-container" class="col-md-4">
                <table id="specific-table" class="table table-bordered cb-table">  
                    <tbody>
                        <tr>
                            <th scope="row">Stock Name</th>
                            <td>${s.stockName}</td>
                        </tr>
                        <tr>
                            <th scope="row">Stock Symbol</th>
                            <td>${s.stockSymbol}</td>
                        </tr>
                        <tr>
                            <th scope="row">Price</th>
                            <td>$${s.sharePrice}</td>
                        </tr>
                        <tr>
                            <th scope="row">Stock Type</th>
                            <td>${s.stockType}</td>
                        </tr>
                        <tr>
                            <th scope="row">Shares Avaliable</th>
                            <td>${s.numberOfSharesAvaliable}</td>
                        </tr>
                        <tr>
                            <th scope="row">Stock Date</th>
                            <td>${s.stockDate}</td>
                        </tr>
                        <tr>
                            <th scope="row">Stock Time</th>
                            <td>${s.stockTime}</td>
                        </tr>
                    </tbody>                    
                </table>
            </div>
        </div>      

        <div id="chart-holder">
            <div id="chart-overlay">
                <div> <h1> Sorry, no results for ${m} months back </h1> </div>
            </div> 
            <div id="chartContainer"></div>               
        </div>
        <div id="chart-control">
            <h4> Currently Showing ${m} Months Back </h4>
            <label for="months">Adjust Months:</label>
            <input id="months" name="months" class="form-control buy-text" type="text" value="${m}">
        </div>
    </div>

</div>


<jsp:include page="footer.jsp"/>
