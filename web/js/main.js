/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
        var helpBuy = true;
        var helpSell = true;
        var helpSearch = true;
        var helpHoldings = true;
        var helpConditional = true;
        var helpSuggestion = true;
        var helpHistory = true;
        
        $(".stockListing").each(function(){
            $(this).click(function(){
                var stockSymbol = $(this).find("th").text();
                $(location).attr("href","/loadSpecificStockPage?val=" + stockSymbol);               
           });          
        });
        
        $("#help-buy-answ").hide();
        $("#help-buy").click(function() {
            if(helpBuy) {
                $("#help-buy-answ").show();
                helpBuy = false;
                $("#help-buy span").removeClass("glyphicon-chevron-right");
                $("#help-buy span").addClass("glyphicon-chevron-down");
            } else {
                $("#help-buy-answ").hide();
                helpBuy = true;
                $("#help-buy span").removeClass("glyphicon-chevron-down");
                $("#help-buy span").addClass("glyphicon-chevron-right");
            }
        });
        
        $("#help-sell-answ").hide();
        $("#help-sell").click(function() {
            if(helpSell) {
                $("#help-sell-answ").show();
                $("#help-sell span").removeClass("glyphicon-chevron-right");
                $("#help-sell span").addClass("glyphicon-chevron-down");
                helpSell = false;
            } else {
                $("#help-sell-answ").hide();
                $("#help-sell span").removeClass("glyphicon-chevron-down");
                $("#help-sell span").addClass("glyphicon-chevron-right");
                
                helpSell = true;
            }           
        });
        
        $("#help-search-answ").hide();
        $("#help-search").click(function() {
            if(helpSearch) {
                $("#help-search-answ").show();
                $("#help-search span").removeClass("glyphicon-chevron-right");
                $("#help-search span").addClass("glyphicon-chevron-down");
                helpSearch = false;
            } else {
                $("#help-search-answ").hide();
                $("#help-search span").removeClass("glyphicon-chevron-down");
                $("#help-search span").addClass("glyphicon-chevron-right");
                
                helpSearch = true;
            }           
        });
        
        $("#help-history-answ").hide();
        $("#help-history").click(function() {
            if(helpHistory) {
                $("#help-history-answ").show();
                $("#help-history span").removeClass("glyphicon-chevron-right");
                $("#help-history span").addClass("glyphicon-chevron-down");
                helpHistory = false;
            } else {
                $("#help-history-answ").hide();
                $("#help-history span").removeClass("glyphicon-chevron-down");
                $("#help-history span").addClass("glyphicon-chevron-right");
                
                helpHistory = true;
            }           
        });
        
        $("#help-holdings-answ").hide();
        $("#help-holdings").click(function() {
            if(helpHoldings) {
                $("#help-holdings-answ").show();
                $("#help-holdings span").removeClass("glyphicon-chevron-right");
                $("#help-holdings span").addClass("glyphicon-chevron-down");
                helpHoldings = false;
            } else {
                $("#help-holdings-answ").hide();
                $("#help-holdings span").removeClass("glyphicon-chevron-down");
                $("#help-holdings span").addClass("glyphicon-chevron-right");
                
                helpHoldings = true;
            }           
        });
        
        $("#help-conditional-answ").hide();
        $("#help-conditional").click(function() {
            if(helpConditional) {
                $("#help-conditional-answ").show();
                $("#help-conditional span").removeClass("glyphicon-chevron-right");
                $("#help-conditional span").addClass("glyphicon-chevron-down");
                helpConditional = false;
            } else {
                $("#help-conditional-answ").hide();
                $("#help-conditional span").removeClass("glyphicon-chevron-down");
                $("#help-conditional span").addClass("glyphicon-chevron-right");
                
                helpConditional = true;
            }           
        });
        
        $("#help-suggestion-answ").hide();
        $("#help-suggestion").click(function() {
            if(helpSuggestion) {
                $("#help-suggestion-answ").show();
                $("#help-suggestion span").removeClass("glyphicon-chevron-right");
                $("#help-suggestion span").addClass("glyphicon-chevron-down");
                helpSuggestion = false;
            } else {
                $("#help-suggestion-answ").hide();
                $("#help-suggestion span").removeClass("glyphicon-chevron-down");
                $("#help-suggestion span").addClass("glyphicon-chevron-right");
                
                helpSuggestion = true;
            }           
        });
        
      
});


