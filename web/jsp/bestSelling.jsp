<%-- 
    Document   : bestSelling
    Created on : Apr 30, 2016, 5:33:32 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="header.jsp">
            <jsp:param name="title" value="Best Selling Stock"/>
            <jsp:param name="css" value="" />
            <jsp:param name="js" value=""/>
        </jsp:include>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <div class="display-content customerTabs col-md-12" 
             style="background-color: rgba(0,0,0,0.7); border-radius: 15px;
             color: white; height:50vh; font-size: 20px;">

            <h1>Best Selling Stocks</h1>
            
            <table class = "table" id="bestSelling">
                <thead>
                    <tr>
                        <th>Rank</th>
                        <th>Stock Symbol</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="e" varStatus="i" items="${bestSellerList}" >
                        <tr>
                            <td>${i.index +1 }</td>
                            <td>${e.stockSymbol}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
