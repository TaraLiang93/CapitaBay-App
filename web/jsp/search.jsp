<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="${requestScope.searchResultsName} Results"/>
    <jsp:param name="css" value="/css/style.css" />
    <jsp:param name="js" value="/js/script1.js" />
</jsp:include>

<c:forEach var="result" items="${keywordResult}">
${result.stockSymbol}
</c:forEach>
<jsp:include page="footer.jsp" />