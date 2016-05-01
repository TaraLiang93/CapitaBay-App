<jsp:include page="header.jsp">
    <jsp:param name="title" value="Capita Bay Home"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/script1.js" />
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="container">
        <div id="table-container" class="content">
     <table class="table table-bordered table-hover cb-table">
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Stock Name</th>
            <th>Stock Type</th>
            <th>Stock Price</th>
            <th>Avaliable Shares</th>       
          </tr>
        </thead>
        <tbody>
            <c:forEach var="stock" items="${s}">    
                <tr class="stockListing">
                    <th scope="row">${stock.stockSymbol}</th>
                    <td>${stock.stockName}</td>
                    <td>${stock.stockType}</td>
                    <td>$${stock.sharePrice}</td>
                    <td>${stock.numberOfSharesAvaliable}</td>
               </tr>
          </c:forEach>         
        </tbody>
     </table>
        </div>

</div>


<jsp:include page="footer.jsp"/>