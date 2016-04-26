<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/script1.js" />
</jsp:include>
<jsp:useBean id="s" class="Tables.Stock" scope="request"/>
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
        });
</script>

    <div class="container">
        <div id="chart-container" class="content">
            <h2 id="specific-title">${s.stockSymbol}</h2>
            <div id="chartContainer"></div>
            <div >
                <table id="specific-table" class="table table-bordered cb-table">  
                    <tbody>
                      <tr>
                        <th scope="row">Stock Symbol</th>
                        <td>${s.stockSymbol}</td>
                        <th scope="row">Price</th>
                        <td>$1200</td>
                      </tr>
                      <tr>
                        <th scope="row">Stock Type</th>
                        <td>Automotive</td>
                        <th scope="row">Shares Avaliable</th>
                        <td>3100</td>
                      </tr>
                    </tbody>
                </table>
            </div>
            <hr id="specific-separator">
            <div id="buy-form">            
            <form method="post" action="demoform.asp">
                <h2> Buy this Stock </h2>
              <!--  <input type="range" name="shares" id="shares" value="0" min="0" max="100" data-highlight="true" data-show-value="true">
            </form>-->
              <fieldset class="form-group">
            <label for="shares">Number of Shares:</label>
            <input id="shares" type="range" name="shares" onchange="alert(this.value)" value="0" min="0" max="100" data-highlight="true" data-show-value="true" />
            <input id="number-shares" class="form-control" type="text" value="0">
            <input id="price-shares" class="form-control" type="text" value="0">
              </fieldset>
            </form>
            </div>
        </div>
        
    </div>


<jsp:include page="footer.jsp"/>
