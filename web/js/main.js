/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

        $(".stockListing").each(function(){
            $(this).click(function(){
                var stockSymbol = $(this).find("th").text();
                $(location).attr("href","/loadSpecificStockPage?val=" + stockSymbol);               
           });          
        });
        
        $("#help-buy").click();
        
});


