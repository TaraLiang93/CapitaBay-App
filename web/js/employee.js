/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $(".deleteEmployee").each(function () {
        $(this).click(function () {
            var parent = $(this).parent().parent();
            $.post("/deleteEmployee", {"ssn": $(this).parent().find(".EmployeeId").text()}
            ).done(function () {
                console.log("delete success");
                parent.remove();
            }).fail(function () {
                console.log("could not delete");
            });
        });
    });



    $(".saveChanges").each(function () {
        $(this).click(function () {

            $.post("/editEmployee", {"employeeSSN": $(this).parent().parent().find(".EmployeeId").text(),
                "employeePos": $(this).parent().parent().find(".employeePos").val(),
                "hourRate": $(this).parent().parent().find("[name=hourRate]").val()})
                    .done(function () {
                        console.log("Updated employee");
                    }).fail(function () {
                console.log("can't update employee");
            });
        });
    });

    $(".hireEmployee").click(function () {

        $("#newEmployee").submit(function () {
            $.post("/newEmployee", $("#newEmployee").serialize())
                    .done(function (e) {
//                       e.preventDefault();
                        alert("It finished");
                        var code = "<tr>" +
                                "<th><button class='tbn btn-danger deleteEmployee'> <i class='glyphicon glyphicon-remove'></i></button> <div class='EmployeeId' style='display:none'>" + $("#SSN").val() + "</div></th>" +
                                "<th>" + $("#firstName").val() + "</th>" +
                                "<th>" + $("#LastName").val() + "</th>" +
                                "<th>" + $("#address").val() + "</th>" +
                                "<th>" + $("#phoneNum").val() + "</th>" +
                                "<th>" + $("#zip").val() + "</th>" +
                                "<th><select class='employeePos'><option name='Manager' value='Manager'>Manager</option><option name='CustomerRep' value='CustomerRep'>Customer Rep</option></select></th>"
                        "<th>" + $("#newHourlyRate").val() + "</th>" +
                                "<th><a class='saveChanges btn btn-primary'>Save</a></th>" +
                                "</tr>";
                        $(".employeeData").append(code);

                    }).fail(function () {
                console.log("didn't make a new employee");
            });
        });


    });

    $(".updateStock").each(function () {
        $(this).click(function () {

            var jsonObj = {"StockSymbol": $(this).parent().parent().find(".stockSymbol").text(),
                "SharePrice": $(this).parent().parent().find(".sharePrice").val()};
            $.post("/UpdateStockPrice", jsonObj).done(function () {
                console.log("updated the stock")
            }).fail(function () {
                console.log("failed  to updated the song");
            });


        });

    });

    $(".searchOrders").click(function () {

        var jsonObj = {};

        jsonObj["customerName"] = $(".search").val() == "customerName" ?
                $("#orderSearch").val() : "";
        jsonObj["stockSymbol"] = $(".search").val() == "stockSymbol" ?
                $("#orderSearch").val() : "";

        $.get("/ListOrders", jsonObj, "json")
                .done(function (data) {
                    console.log("I got the data back");
                    console.log(data);
                    var code = "";
                    $("#orderSerchResults").html("");
                    $.each(data.orders, function (key, value) {
                        console.log("key: " + key + " value: " + value);
                        code = "";
                        code = "<tr>" +
                                "<td>" + value.oid + "</td>" +
                                "<td>" + value.ot + "</td>" +
                                "<td>" + value.c_ssn + "</td>" +
                                "<td>" + value.e_ssn + "</td>" +
                                "<td>" + value.ss + "</td>" +
                                "<td>" + value.an + "</td>" +
                                "<td>" + value.o_date + "</td>" +
                                "<td>" + value.o_time + "</td>" +
                                "<td>" + value.nos + "</td>" +
                                "<td>" + value.sp + "</td>" +
                                "</tr>";

                        $("#orderSerchResults").append(code);

//                     code
                    });


//                 console.log(code);



                }).fail(function () {
            console.log("it failed");
        });
    });

    $(".richestRep").click(function () {
        $.get("/RichestRep").done(function (data) {
            console.log("it passed");
            $(".richestRepInput").append(
                    $('<h3></h3>').text("And the most richest Customer rep is"),
                    $('<table></table>').addClass("table table-hover").append(
                    $('<thead></thead>').append(
                    $('<tr></tr>').append(
                    $('<th></th>').text("First Name"),
                    $('<th></th>').text("Last Name"),
                    $('<th></th>').text("SSN"),
                    $('<th></th>').text("Revenue")
                    )
                    ),
                    $('<tbody></tbody>').append(
                    $('<tr></tr>').append(
                    $('<td></td>').text(data.firstname),
                    $('<td></td>').text(data.lastName),
                    $('<td></td>').text(data.ssn),
                    $('<td></td>').text(data.revenue)
                    )
                    )
                    )
                    );

        }).fail(function () {
            console.log("all i do is skate and smoke and skate and fuck");
        });
    });

    $(".searchRevBtn").click(function () {

        var url;

        switch ($(".searchRev").val()) {
            case "Stock":
                url = "/revenueByStock";
                break;
            case "StockType":
                url = "/revenueByStockType";
                break;
            case "Customer":
                url = "/RevenueByCustomer";
                break;
        }

        if ($("#searchRevenue").val() == "")
            return;
        $.get(url, {"val": $("#searchRevenue").val()}, "json").done(function (data) {
            $(".revTable").hide();
            switch (data.table)
            {
                case "revCustomerTable" :
                    buildCustomerRevTable(data.customer);
                    break;
                case "revStockTypeTable" :
                    buildStockTypeRevTable(data.stocktype);
                    break;
                case "revStockTable":
                    buildStockRevTable(data.stock);
                    break;
            }

        })
                .fail(function () {
                    console.log("it failed to get the revenue");
                });

    });
    
    
    $(".salesReportMonth").change(function(){
        console.log($(this).val());
        
        $.get("/GetSalesReportForMonth",{"month": $(this).val()},"json")
                .done(function(data){
                    console.log(data);
            $(".salesReport").find("table").remove();
            
            $(".salesReport").append(
                        $('<table></table>').addClass("table table-condensed table-hover col-xs-12").append(
                            $("<thead></thead>").append(
                                $("<tr></tr>").append(
                                    $("<th></th>").html("Order ID"),
                                    $("<th></th>").html("Employee ID"),
                                    $("<th></th>").html("Customer ID"),
                                    $("<th></th>").html("Account Number"),
                                    $("<th></th>").html("Stock Symbol"),
                                    $("<th></th>").html("Order Type"),
                                    $("<th></th>").html("Order Date"),
                                    $("<th></th>").html("Order Time"),
                                    $("<th></th>").html("Share Price"),
                                    $("<th></th>").html("Shares Bought")
                                )
                            ),
                            $("<tbody></tbody>")
                        )
                    );
            $.each(data.salesReport, function(k, v){
                $(".salesReport").find("tbody").append(
                            $("<tr></tr>").append(
                                $("<td></td>").html(v.oid),
                                $("<td></td>").html(v.e_ssn),
                                $("<td></td>").html(v.c_ssn),
                                $("<td></td>").html(v.an),
                                $("<td></td>").html(v.ss),
                                $("<td></td>").html(v.ot),
                                $("<td></td>").html(v.o_date),
                                $("<td></td>").html(v.o_time),
                                $("<td></td>").html(v.sp),
                                $("<td></td>").html(v.nos)
                            )
                        );
            });
            
                    
        })
        .fail(function() {
            console.log("it failed to come here");
        })
    });

});

function buildCustomerRevTable(table) {
    $("#revCustomerTable").html("");
    $("#revCustomerTable").append(
            $("<thead></thead>").append(
            $("<tr></tr>").append(
            $("<th></th>").html("Customer ID"),
            $("<th></th>").html("First Name"),
            $("<th></th>").html("Last Name"),
            $("<th></th>").html("Revenue")
            )
            ),
            $("<tbody></tbody>")

            );
    $.each(table, function (key, value) {
        $("#revCustomerTable").find("tbody").append(
                $("<tr></tr>").append(
                $("<td></td>").html(value.ssn),
                $("<td></td>").html(value.firstname),
                $("<td></td>").html(value.lastname),
                $("<td></td>").html(value.revenue)
                )
                );
    });
    $("#revCustomerTable").show();

}

function buildStockRevTable(table) {

    $("#revStockTable").html("");
    $("#revStockTable").append(
            $("<thead></thead>").append(
            $("<tr></tr>").append(
            $("<th></th>").html("Stock Symbol"),
            $("<th></th>").html("Revenue")
            )
            ),
            $("<tbody></tbody>")
            );
    $.each(table, function (key, value) {
        $("#revStockTable").find("tbody").append(
                $("<tr></tr>").append(
                $("<td></td>").html(value.ss),
                $("<td></td").html(value.rev)
                )
                );
    });
    $("#revStockTable").show();


}
function buildStockTypeRevTable(table) {

    $("#revStockTypeTable").html("");
    $("#revStockTypeTable").append(
            $("<thead></thead>").append(
            $("<tr></tr>").append(
            $("<th></th>").html("Stock Type"),
            $("<th></th>").html("Revenue")
            )
            ),
            $("<tbody></tbody>")
            );
    $.each(table, function (key, value) {
        $("#revStockTypeTable").find("tbody").append(
                $("<tr></tr>").append(
                $("<td></td>").html(value.st),
                $("<td></td>").html(value.rev)
                )
                )
    });
    $("#revStockTypeTable").show();
}

