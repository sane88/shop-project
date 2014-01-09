
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/main.css"/>
</head>
<body>
<div class="container">

    <div class="form-signin" role="form" id="register-form">
        <h2 class="form-signin-heading">Please register</h2>
        <input type="text" class="form-control" placeholder="Login" required autofocus="" id="login-input">
        <input type="password" class="form-control" placeholder="Password" required="" id="registration-password-input">
        <input type="password" class="form-control" placeholder="Re-enter password" required="" id="re-password-input">
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="register-btn">Register</button>
    </div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/authorization.js"></script>
<script type="text/javascript" src="js/notify.js"></script>
</html>