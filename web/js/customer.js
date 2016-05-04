/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#gcoSubmit").click(function () {

        $.get("/ListConditionalOrderHistory", {"orderID": $("#orderID").val()},"json")
                .done(function (data) {

                    var code = "";
//                    var index = 0;
//                    for(; index<data.history.length; index++){
//                        console.log("yaya")
//                    }
//                    data = $.parseJSON('' + data + '');
                    var test = data;
                    console.log(data);
                    $.each(data.history, function (key, value) {
                        console.log("key: " + key + " value: " + value);
                        code = "";
                        code = "<tr>" +
                                "<td>" + value.sharePrice + "</td>" +
                                "<td>" + value.stockDate + "</td>" +
                                "</tr>";

                        $("#condHistory").append(code);

//                     code
                    });
                })
                .fail(function (data) {
//                    $(".failMessage").val("Cannot get information on this order.");
                    console.log("failed on the server");
                });

    });

    $(".stockSymbol").each(function () {
        $(this).click(function () {
            var stockSymbol = $(this).text();
            $(location).attr("href", "/loadSpecificStockPage?val=" + stockSymbol);
        });
    });



    $("[type=range]").change(function () {
        var newval = $(this).val();
        $("#shareNums").val(newval);
        $("#shares-upper").text(newval);
        var price = $("#currentPrice").text();
        price = price * newval;
        $("#sellPrices").val(price);

    });
    

    $(".saveChanges").each(function () {
        $(this).click(function () {
            var parent = $(this).parent().parent();
            $(".stockSymbol").val(parent.find(".stockSymbol").text());
            $("#numShares").text(parent.find(".totalShares").text());
            $("#currentPrice").text(parent.find(".price").text());
            $(".acctNum").val(parent.find(".accountNumber").text());
            $("#shares-upper").attr({max: parent.find(".totalShares").text()});
            $("#shares-upper").text(parent.find(".totalShares").text());
            $("#shares").attr({max: parent.find(".totalShares").text()});
            $("#stockModal").modal("show");
        });
    });

    /* ORIGINAL CODE */
//    $(".coSubmit").click(function () {
//        $.get("/GetConditionalOrderHistory", {"orderID": $("#orderID").val()}, "json")
//                .done(function (data) {
//                    var code = "";
//                    $.each(data.history, function (key, value) {
//                        console.log("key: " + key + " value: " + value);
//                        code = "";
//                        code = "<tr>" +
//                                "<td>" + value.sharePrice + "</td>" +
//                                "<td>" + value.stockDate + "</td>" +
//                                "</tr>";
//
//                        $("#condHistory").append(code);
//
////                     code
//                    });
//                })
//                .fail(function () {
//                    $(".failMessage").val("Cannot get information on this order.");
//                    console.log("sumbiting and getting order history failed");
//                });
//    });
});
