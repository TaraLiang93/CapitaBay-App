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
        
        $(".saveChanges").each(function() {
           $(this).click(function() {
               ''
               $.post("/editEmployee",{"employeeSSN" : $(this).parent().parent().find(".EmployeeId").text(), 
                                       "employeePos" : $(this).parent().parent().find(".employeePos").val(),
                                       "hourRate" : $(this).parent().parent().find("[name=hourRate]").val() })
                                           .done(function() {
                                               console.log("Updated employee");
                                       }).fail(function() {
                                           console.log("can't update employee");
                                       });
           }); 
        });
        
    });
});

