/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $(".stock").each(function(){
        $(this).click(function() {
            $(location).attr("href", "/loadSpecificStockPage?val=" + $(this).find(".stockSymbol").text());
        });
        
    });
});

