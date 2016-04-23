<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/script1.js" />
</jsp:include>
<jsp:useBean id="s" class="Tables.Stock" scope="request"/>
<script>
    window.onload = function () {
	var chart = new CanvasJS.Chart("chartContainer", {
		title:{
			text: ""              
		},
                axisX:{
                    labelFontColor: "#333"
                  },
                axisY:{
                    interlacedColor: "#CCC",
                    gridColor: "darkgreen",
                    labelFontColor: "#333"
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
        };
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
            <div id="buy-form">            
            <!--<form method="post" action="demoform.asp">
                <label for="shares">Number of Shares:</label>
                <input type="range" name="shares" id="shares" value="0" min="0" max="100" data-highlight="true" data-show-value="true">
            </form>-->
            <input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="14"/>
            </div>
        </div>
        
    </div>


<jsp:include page="footer.jsp"/>