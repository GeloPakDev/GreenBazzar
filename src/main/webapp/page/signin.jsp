<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/login-style.css">
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<header>
    <div class="header-main">
        <div class="container">
            <span class="title" style="font-size: 31px;font-weight: 500;position: absolute;top: 31px;left: 220px;">Greenbazaar</span>
            <span class="enter" style="font-size: 21px;font-weight: 400;position: absolute;top: 37px;right: 220px;"> <a
                    style="color: black;text-decoration: none;" class="small"
                    href="${pageContext.request.contextPath}/page/signup.jsp">Create an account!</a></span>
        </div>
    </div>
</header>
<div class="container">
    <div class="container">
        <div class="title">Login</div>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="login" name="command">

            <div class="user_details">
                <div class="input_box">
                    <input type="text" name="login" placeholder="Login" required>
                </div>
                <div class="input_box">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
            </div>

            <div class="button">
                <input type="submit" value="Login">
            </div>
        </form>
    </div>
</div>
</body>
</html>