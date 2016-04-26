<jsp:include page="header.jsp">
    <jsp:param name="title" value="${name} home"/>
    <jsp:param name="css" value="/css/employee.css" />
</jsp:include>

<div class="display-content employeeTabs col-md-12">
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
    <li role="presentation"><a href="#staff" aria-controls="staff" role="tab" data-toggle="tab">Edit Staff</a></li>
    <li role="presentation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">Orders</a></li>
    <li role="presentation"><a href="#customrs" aria-controls="customers" role="tab" data-toggle="tab">Edit Customers</a></li>
    <li role="presentation"><a href="#stock" aria-controls="stock" role="tab" data-toggle="tab">Stock</a></li>
</ul>

 <div class="tab-content">
     <div role="tabpanel" class="tab-pane active" id="home">
         Home page should display what it needs too
     </div>
     <div role="tabpanel" class="tab-pane" id="staff">
         is the start
     </div>
     <div role="tabpanel" class="tab-pane" id="orders">
         Jesus
     </div>
     <div role="tabpanel" class="tab-pane" id="customrs">
         Love
     </div>
     <div role="tabpanel" class="tab-pane" id="stock">
         God
     </div>
  </div>
</div>
<jsp:include page="footer.jsp"/>