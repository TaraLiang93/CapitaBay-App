/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $(".deleteEmployee").each(function(){
        $(this).click(function() {
            var parent = $(this).parent().parent();
            $.post("/deleteEmployee", {"ssn": $(this).parent().find(".EmployeeId").text()}
                    ).done(function() {
                        console.log("delete success");
                        parent.remove();
            }).fail(function() {
               console.log("could not delete"); 
            });
        });
    });
});

