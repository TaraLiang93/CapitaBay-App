/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){

    $("#loginForm").bind('ajax:complete', function(data){
        console.log("inside of ajax complete");
        alert(data);
    });
});


