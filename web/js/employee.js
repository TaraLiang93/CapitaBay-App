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
        
        
        
        $(".saveChanges").each(function() {
           $(this).click(function() {

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
        
        $(".hireEmployee").click(function() {

            $("#newEmployee").submit(function() {
                                           $.post("/newEmployee",$("#newEmployee").serialize())
                   .done(function(e) {
//                       e.preventDefault();
                                     alert("It finished");
                       var code = "<tr>" +
                        "<th><button class='tbn btn-danger deleteEmployee'> <i class='glyphicon glyphicon-remove'></i></button> <div class='EmployeeId' style='display:none'>"+$("#SSN").val()+"</div></th>" +
                        "<th>"+$("#firstName").val()+"</th>" +
                        "<th>"+$("#LastName").val()+"</th>" +
                       "<th>"+$("#address").val()+"</th>" +
                        "<th>"+$("#phoneNum").val()+"</th>" +
                         "<th>"+$("#zip").val()+"</th>" +
                         "<th><select class='employeePos'><option name='Manager' value='Manager'>Manager</option><option name='CustomerRep' value='CustomerRep'>Customer Rep</option></select></th>"
                         "<th>"+$("#newHourlyRate").val()+"</th>" + 
                         "<th><a class='saveChanges btn btn-primary'>Save</a></th>" +
                                  "</tr>";
                       $(".employeeData").append(code);

           }).fail(function() {
               console.log("didn't make a new employee");
           });
            });


        });
        
        $(".updateStock").each(function() {
            $(this).click(function() {
                
                var jsonObj = {"StockSymbol" : $(this).parent().parent().find(".stockSymbol").text(),
                    "SharePrice" : $(this).parent().parent().find(".sharePrice").val()};
                $.post("/UpdateStockPrice",jsonObj).done(function(){
                console.log("updated the stock")
            }).fail(function() {
                console.log("failed  to updated the song");
            });
           

        });
        
    });
    
    $(".searchOrders").click(function() {
        
       var jsonObj = {};
       
       jsonObj["customerName"] = $(".search").val() == "customerName" ? 
                                 $("#orderSearch").val() : "";
       jsonObj["stockSymbol"] =  $(".search").val() == "stockSymbol" ? 
                                 $("#orderSearch").val() : "";
        
       $.get("/ListOrders",jsonObj,"json")
               .done(function(data){
                 console.log("I got the data back");
                 console.log(data);
       }).fail(function() {
           console.log("it failed");
       }); 
    });

});

