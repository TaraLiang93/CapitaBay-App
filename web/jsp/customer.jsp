<%-- 
    Document   : customer
    Created on : Apr 28, 2016, 5:41:51 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${name} home"/>
            <jsp:param name="css" value="/css/customer.css" />
            <jsp:param name="js" value="/js/customer.js"/>
        </jsp:include>
    </head>
    <body>


        <div class="display-content customerTabs col-md-12" 
             style="background-color: rgba(0,0,0,0.7); border-radius: 15px;
             color: white; height:80vh;">
            
            <h1>${userBean.username }'s Profile</h1>       
            
<!--                 Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                    <li role="presentation"><a href="#stockHolding" aria-controls="stockHolding" role="tab" data-toggle="tab">Current Stock Holding</a></li>
                    <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Order History</a></li>
                    <li role="presentation"><a href="#sugesstions" aria-controls="sugesstions" role="tab" data-toggle="tab">Stock Sugesstion</a></li>
                    <li role="presentation"><a href="#blah" aria-controls="blah" role="tab" data-toggle="tab">blah</a></li>


                </ul>

<!--                 Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <form action="/logOutServlet" method="GET">
                            <button class="btn btn-default" type="submit">Log Out</button>
                        </form>
                    </div>
                  
                    
                    <div role="tabpanel" class="tab-pane" id="stockHolding">
                        stock holding
                    </div>
                    
                    
                    <div role="tabpanel" class="tab-pane" id="orders">
                        order History
                    </div>
                    
                    
                    <div role="tabpanel" class="tab-pane" id="sugesstions">
                        order sugesstion
                    </div>
                    
                    
                    <div role="tabpanel" class="tab-pane" id="blah">
                        blah
                    </div>
                </div>
            
            
            
            
            
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
