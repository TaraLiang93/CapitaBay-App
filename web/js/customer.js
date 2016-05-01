/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#orderID").submit(function () {
        $.get("/GetConditionalOrderHistory", {"orderID": $("#orderID").val()}, "json")
                .done(function (e) {
                    var code = "";
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
                .fail(function () {
                    console.log("sumbiting and getting order history failed");
                });
    });
});
