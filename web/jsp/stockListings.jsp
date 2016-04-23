<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/script1.js" />
</jsp:include>

    <div class="container">
        <div id="table-container" class="content">
     <table class="table table-bordered table-hover cb-table">
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Stock Name</th>
            <th>Stock Price</th>
            <th>Avaliable Shares</th>       
          </tr>
        </thead>
        <tbody>
          <tr class="stockListing">
            <th scope="row">IBM</th>
            <td >IBM</td>
            <td>$1500</td>
            <td>1100</td>
          </tr>
          <tr class="stockListing">
            <th scope="row">F</th>
            <td>Ford</td>
            <td>$1000</td>
            <td>1500</td>

          </tr>
          <tr class="stockListing">
            <th scope="row">GM</th>
            <td>General Motors</td>
            <td>$1200</td>
            <td>2000</td>
           
          </tr>
        </tbody>
     </table>
        </div>

</div>


<jsp:include page="footer.jsp"/>