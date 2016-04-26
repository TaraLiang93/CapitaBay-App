<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link href='https://fonts.googleapis.com/css?family=VT323' rel='stylesheet' type='text/css'>
        <link href="/css/style.css" rel='stylesheet' type='text/css'>      
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="scripts/jquery.mb.browser.min.js"></script>
        <script src="/js/script1.js"></script>
        <script src="js/canvasjs.min.js"></script>
        <script src="js/main.js"></script>
        <c:if test="${not empty param.js}">
            <c:forEach items = "${param.js}" var = "file">
                <script src="${file}"></script>
            </c:forEach>
        </c:if>

        <c:if test="${not empty param.css}">
            <c:forEach items = "${param.css}" var = "style">
                <link rel = "stylesheet" href="${style}">
            </c:forEach>
        </c:if>

        <!--<meta name=viewport content="width=device-width, initial-scale=1">-->
        <title>${param.title}</title>

        <!--<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />-->
    </head>
    <body>

            <nav class="navbar navbar-default navbar-fixed-top" id="header">
                <div id="top-header" class="container"> 
                    <img src="/images/capitabay_logo.png" id="logo1"/>
                    <div id="header-carousel" class="carousel slide" data-ride="carousel">


                        <div class="carousel-inner" role="listbox">
                            <div class="item active up">
                                <h2>
                                    IBM +0.1%
                                </h2>
                            </div>

                            <div class="item down">
                                <h2>
                                    Ford -0.25%
                                </h2>
                            </div>

                            <div class="item up">
                                <h2>
                                    GM +1.03%
                                </h2>
                            </div>
                        </div>
                    </div>
                    <div id="user-portal">
                        <span id="welcome"> Welcome </span>
                        <c:choose>
                            <c:when test = "${userBean.username == null}">
                                <a href="/jsp/loginPage.jsp" id="user">Hello</a>
                            </c:when>
                            <c:when test="${userBean.username != null}">
                                <a href="/jsp/loginPage.jsp" id="user">${userBean.username}</a>

                            </c:when>
                        </c:choose> 
                    </div>
                </div>
                <div class="container">           
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="#about" id="nav-home">Capita Bay</a></li>
                            <li><a href="#about">About</a></li>
                            <li><a href="/loadStockListingsPage">Trading</a></li>
                            <li><a href="#contact">Statistics</a></li>
                            <li><a href="../navbar/">Help</a></li>
                            <!--<li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Stock Market<span class="caret"></span></a>
                              <ul class="dropdown-menu">
                                <li><a href="#">Stock Listings</a></li>
                                <li><a href="#">Buy/Sell Stocks</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li class="dropdown-header">Nav header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
                              </ul>
                            </li>-->
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </nav>
    </body>
</html>
