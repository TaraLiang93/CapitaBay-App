<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/specificStock.js" />
</jsp:include>
<script>
    $(document).ready(function(){
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
				{ label: "4/10", y: 51 },
				{ label: "4/11", y: 45},
				{ label: "4/12", y: 50 },
				{ label: "4/13", y: 62 },
				{ label: "4/14", y: 95 },
				{ label: "4/15", y: 66 },
				{ label: "4/16", y: 24 },
				{ label: "4/17", y: 32 },
				{ label: "4/18", y: 90 }
			]
			}
                ]
	});
	chart.render();
        
        $("[type=range]").change(function(){
            var newval=$(this).val();
            $("#number-shares").val(newval);
            $("#price-shares").val(parseFloat(Math.round(newval * ${s.sharePrice} * 100) / 100).toFixed(2));
        });
        
        $("#number-shares").change(function(){
            var newval=$(this).val();
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
        
        });
</script>

    <div class="container">
        <div id="chart-container" class="content">
            
            <h2 id="specific-title">${s.stockName}</h2>
            <div class="row">
            <div id="buy-form" class="col-md-8">            
                <form method="post" >
                    <h2> Buy this Stock </h2>                       
                        <h3 id="shares-lower" class="shares-bounds"> 0 </h3>
                        <input id="shares" type="range" name="shares"
                               value="0" min="0" max="${s.numberOfSharesAvaliable}" data-highlight="true" data-show-value="true" />  
                        <h3 id="shares-upper" class="shares-bounds"> ${s.numberOfSharesAvaliable} </h3>
                        <br><br><br>
                        <div class="row">
                        <div class="col-md-6">
                        <label for="number-shares">Number of Shares:</label>
                        <input id="number-shares" class="form-control buy-text" type="text" value="0">
                        </div>
                        <div class="col-md-6">
                        <label for="price-shares">Total Price:</label>
                        <input id="price-shares" class="form-control buy-text" type="text" value="$0">
                        </div>
                        </div>
                        <input id="buy-submit" name="submit" class="btn btn-success" value="Buy Shares" type="submit">
                </form>
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
                      <table>
                        <c:forEach var="stock" items="${h}">
        <tr class="stock"> 
            <td>${stock.sharePrice}</td>
        </tr>
    </c:forEach>
        </table>
        
                   
            <div id="chartContainer"></div>
                                  
        </div>
        
    </div>


<jsp:include page="footer.jsp"/>
