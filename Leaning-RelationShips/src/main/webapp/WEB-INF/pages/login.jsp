
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In Page</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/main.css"/>
</head>
<body>
<div class="container">

    <div class="form-signin" role="form" id="login-form">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" placeholder="Login" required autofocus="" id="login-input">
        <input type="password" class="form-control" placeholder="Password" required="" id="password-input">
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="login-btn">Sign in</button>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/authorization.js"></script>
<script type="text/javascript" src="js/notify.js"></script>
</html>