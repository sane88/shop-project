
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/main.css"/>
</head>
<body>
<div class="container">
<div class="header">
    <div class="auth-box">

        <nav class="navbar navbar-default" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">E-Store</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <c:if test="${authorized}">
                <ul class="nav navbar-nav">
                    <%--<li class="active"><a href="#">Link</a></li>--%>
                    <li><a href="/cart">My cart</a></li>
                    <li><a href="/wishlist">My wishlist</a></li>
                </ul>
                </c:if>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${!authorized}">
                        <li><a href="/register">Register</a></li>
                        <li><a href="/login">Sign in</a></li>
                    </c:if>
                    <c:if test="${authorized}">
                        <p class="navbar-text">Signed in as ${user}</p>
                        <li><a id="logout-link" href="#">Sign out</a></li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>
</div>
    <div id="message-box">

    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">${place}</h4>
        </div>
        <div class="panel-body">
            <div class="row">
                <c:if test="${not empty items}">
                    <c:forEach items="${items}" var="item">
                        <div class="col-sm-6 col-md-4" id="item-${item.itemId}">
                            <div class="thumbnail">
                                <img src="${item.imageUrl}" alt="${item.name}">
                                <div class="caption">
                                    <h3>${item.name}</h3>
                                    <p>${item.description}</p>

                                        <c:if test="${showBuyBtn}">
                                           <button class="btn btn-primary" role="button" onclick="buy(${item.itemId})">Buy</button>
                                        </c:if>
                                        <c:if test="${showAddToWishBtn}">
                                           <button class="btn btn-default" role="button" onclick="wish(${item.itemId})">Add to wishlist</button>
                                        </c:if>
                                        <c:if test="${showRemoveFromWishBtn}">
                                           <button class="btn btn-default" role="button" onclick="unwish(${item.itemId})">Remove from wishlist</button>
                                        </c:if>

                                    <c:if test="${adminBtns}">

                                        <c:if test="${not empty item.usersBoughtIt}">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                Users bought it
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${item.usersBoughtIt}" var="user">
                                                    <li><a>${user.username}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        </c:if>
                                        <c:if test="${not empty item.usersWishIt}">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                Users wish it
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${item.usersWishIt}" var="user">
                                                  <li><a>${user.username}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        </c:if>

                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/notify.js"></script>
</html>