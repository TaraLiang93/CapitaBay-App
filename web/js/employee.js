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


    $(".asyncForm").submit(function(e){
       e.preventDefault(); 
    });
    
    $(".addUserButton").click(function() {
        
        $.post("/newCustomer", $("#addCustomerForm").serialize())
                .done(function() {
                    $("#addCustomerModal").modal('toggle');
                    console.log("Love");
                    
                    $(".customerTable").find('tbody').append(
                            $("<tr></tr>").append(
                                $("<td></td>").html( "<a class='btn btn-danger removeCustomer'> <i class='glyphicon glyphicon-remove'></i> </a>" ),
                                $("<td></td>").html( $("#cSSN").val() ),
                                $("<td></td>").html("<input type='text' class='form-control customerRating' value='1.0'/>"),
                                $("<td></td>").html( $("#cfirstName").val() + " " + $("#cLastName").val() ),
                                $("<td></td>").html("<input type='text' class='form-control customerCredit' value='"+ $("#ccreditCard").val() +"'/>"),
                                $("<td></td>").html( "<input type='text' class='form-control customerEmail' value='"+$("#cEmail").val() + "' />"), 
                                $("<td></td>").html( $("#caddress").val() ),
                                $("<td></td>").html( $("#czip").val() ),
                                $("<td></td>").html( $("#cphoneNum").val() ),
                                $("<td></td>").html( "<a class='btn btn-primary saveCustomers'><i class='glyphicon glyphicon-floppy-saved' aria-hidden='true'></i></a>" )
                            )
                    );
                    $("#addCustomerModal").modal("close");
        }).fail(function() {
           console.log("Fun loving"); 
        });
    });
    
$("#addEmployeeModal").on('shown.bs.modal', function () {
            
        $("#newEmployee").click(function () {
                $.post("/newEmployee", $("#signup").serialize())
                    .done(function (e) {
//                       e.preventDefault();

                        var code = "<tr>" +
                                "<td><button class='btn btn-danger deleteEmployee'> <i class='glyphicon glyphicon-remove'></i></button> <div class='EmployeeId' style='display:none'>" + $("#SSN").val() + "</div></td>" +
                                "<td>" + $("#firstName").val() + "</td>" +
                                "<td>" + $("#LastName").val() + "</td>" +
                                "<td>" + $("#address").val() + "</td>" +
                                "<td>" + $("#phoneNum").val() + "</td>" +
                                "<td>" + $("#zip").val() + "</th>" +
                                "<td><select class='employeePos form-control new'><option name='Manager' value='Manager'>Manager</option><option name='CustomerRep' value='CustomerRep'>Customer Rep</option></select></td>"+
                                "<td><div class='btn-inline'>$<div class='btn-group'> <input class='col-md-8 input' type='text' name='hourRate' value='"+ $("#newHourlyRate").val() + "' /></div></div></td>" +
                                "<td><a class='saveChanges btn btn-primary'>Save</a></td>" +
                                "</tr>";
                        $(".employeeData").append(code);
                        $(".employeePos.new").attr("value",$("#newPosition").val());
                        $(".employeePos.new").removeClass("new");
                        $("#addEmployeeModal").modal('toggle');

                    }).fail(function () {
                        alert("Cannot add Employee");
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
    
    $(".removeCustomer").each(function() {
        $(this).click(function() {
            var parent  = $(this).parent().parent()
            $.post("/DeleteCustomer",{"customerID" : parent.find(".customerID").text()})
                    .done(function() {
                        console.log("Customer deleted");
                        parent.remove();
            })
                    .fail(function() {
                        console.log("failed to delete");
            });
        });
    });
    
    $(".updateCustomers").each(function() {
        $(this).click(function() {
            var row = $(this).parent().parent();
            
            $.post("/editCustomer",{"customerID" : row.find(".customerID").text(), 
                                    "rating" : row.find(".customerRating").val(),
                                    "creditNum" : row.find(".customerCredit").val(),
                                    "email" :  row.find(".customerEmail").val()
                                    }).done(function(e) {
                                        console.log(e);
                                        console.log("updated successfully");
                                    }).fail(function() {
                                        console.log("failed to update");
                                    });
        });
    });
    
    $(".customerSugestion").each(function() {
        $(this).click(function() {
            
            $.get("/MakeStockSuggestionList", {"suggestionSSN" : $(this).find(".customerSugID").text()},"json")
                    .done(function(data) {
                        console.log("the data came back");
                $(".custSuggestList").html("");
                $(".custSuggestList").append(
                        $("<thead></thead>").append(
                            $("<th></th>").html("Stock Symbol"),
                            $("<th></th>").html("Stock Name"),
                            $("<th></th>").html("Stock Type"),
                            $("<th></th>").html("Share Price"),
                            $("<th></th>").html("Shares Availiable")
                        ),
                        $("<tbody></tbody>")
                        );
                $.each(data.stockSugesst,function(key,value){
                    $(".custSuggestList").find("tbody").append(
                                $("<tr><tr>").append(
                                    $("<td></td>").html(value.ss),
                                    $("<td></td>").html(value.sn),
                                    $("<td></td>").html(value.st),
                                    $("<td></td>").html(value.sp),
                                    $("<td></td>").html(value.nos)
                                )
                            );
                    $(".custSuggestList").show();
                });
                        
            })
                    .fail(function(){
                        console.log("failed to find results");
            });
            
        });
    })

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

