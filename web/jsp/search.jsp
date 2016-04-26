<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="${requestScope.searchResultsName} Results"/>
    <jsp:param name="css" value="/css/search.css" />
    <jsp:param name="js" value="/js/search.js" />
</jsp:include>

<div class="search">
<table class="table table-hover" style="margin-top: 300px">
    
    <thead>
        <tr>
            <th>Stock Symbol</th>
            <th>Stock Type</th>
            <th>Stock Name</th>
            <th>Share Price</th>
            <th>Latest Date</th>
            <th>Latest Time</th>
            <th>Shares Available</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="stock" items="${keywordResult}">
        <tr class="stock"> 
            <td class="stockSymbol">${stock.stockSymbol}</td>
            <td>${stock.stockType}</td>
            <td>${stock.stockName}</td>
            <td>${stock.sharePrice}</td>
            <td>${stock.stockDate}</td>
            <td>${stock.stockTime}</td>
            <td>${stock.numberOfSharesAvaliable}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    
</div>
<jsp:include page="footer.jsp" />