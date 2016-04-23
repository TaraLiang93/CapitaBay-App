window.onload = function () {

	var chart = new CanvasJS.Chart("chartContainer", {
		title:{
			text: "IBM Stock History"              
		},                
                data: [
		{
			type: "spline", //change type to bar, line, area, pie, etc
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
				{ label: "4/18", y: 1600}
			]
			}
                ]
	});
	chart.render();
        };


