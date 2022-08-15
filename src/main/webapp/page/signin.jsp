<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html lang="ru">
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
                    href="${pageContext.request.contextPath}/page/signup.jsp"><fmt:message
                    key="signin.createaccount"/></a></span>
        </div>
    </div>
</header>
<div class="container">
    <div class="container">
        <div class="title"><fmt:message key="title.login"/></div>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="login" name="command">

            <div class="user_details">
                <div class="input_box">
                    <input type="text" name="login" placeholder="<fmt:message key="title.login"/>" required>
                </div>
                <div class="input_box">
                    <input type="password" name="password" placeholder="<fmt:message key="signin.password"/>" required>
                </div>
            </div>

            <div class="button">
                <input type="submit" value="<fmt:message key="title.login"/>">
            </div>
        </form>
    </div>
</div>
</body>
</html>