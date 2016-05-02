/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#gcoSubmit").click(function () {
        
        $.get("/ListConditionalOrderHistory", {"orderID": $("#orderID").val()})
                .done(function (data) {
                    
                    var code = "";
//                    var index = 0;
//                    for(; index<data.history.length; index++){
//                        console.log("yaya")
//                    }
                    data = $.parseJSON(''+data+'');
                    var test = data;
                    console.log(data);
                    $.each(data, function (key, value) {
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



    $(".saveChanges").each(function(){
        $(this).click(function(){
            var parent = $(this).parent().parent();
            alert("You have click edif for: "+parent.find(".stockSymbol").text());
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
