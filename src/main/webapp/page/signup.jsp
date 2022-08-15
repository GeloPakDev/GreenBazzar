<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html lang="ru">

<head>
    <title>Registration Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/registration-style.css">
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<header>
    <div class="header-main">
        <div class="container">
            <span class="title" style="font-size: 31px;font-weight: 500;position: absolute;top: 31px;left: 220px;">Greenbazaar</span>
            <span class="enter" style="font-size: 21px;font-weight: 400;position: absolute;top: 37px;right: 220px;"> <a
                    style="color: black;text-decoration: none;" class="small"
                    href="${pageContext.request.contextPath}/page/signin.jsp"><fmt:message key="title.login"/></a></span>
        </div>
    </div>
</header>
<div class="container">
    <div class="title"><fmt:message key="title.registration"/></div>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" value="registration" name="command">
        <div class="user_details">
            <div class="input_box">
                <input type="text" name="login" placeholder="<fmt:message key="title.login"/>" required>
            </div>
            <div class="input_box">
                <input type="password" name="password" placeholder="<fmt:message key="signin.password"/>" required>
            </div>
            <div class="input_box">
                <input type="text" name="first_name" placeholder="<fmt:message key="signin.firstname"/>" required>
            </div>
            <div class="input_box">
                <input type="text" name="last_name" placeholder="<fmt:message key="signin.lastname"/>" required>
            </div>
        </div>

        <div class="email_input">
            <input type="text" name="email" placeholder="<fmt:message key="signin.email"/>" required>
        </div>

        <div class="roles">
        <span class="custom-control custom-checkbox small">
        <input type="radio" class="custom-control-input" value="CUSTOMER" name="role" id="customer"
               onclick="roleCheck()">
        <label class="custom-control-label" for="customer"><fmt:message key="signin.customer"/></label>
        </span>

            <span class="custom-control custom-checkbox small">
        <input type="radio" class="custom-control-input" value="SELLER" name="role" id="seller" onclick="roleCheck()">
        <label class="custom-control-label" for="seller"><fmt:message key="signin.seller"/></label>
        </span>
        </div>

        <div id="ifSeller" style="visibility:hidden" class="form_control">
            <input type="text" id="company" class="form-control form-control-user" name="company"
                   placeholder="<fmt:message key="signin.companyname"/>">
        </div>

        <br>
        <script>
            function roleCheck() {
                if (document.getElementById('seller').checked) {
                    document.getElementById('ifSeller').style.visibility = 'visible';
                } else document.getElementById('ifSeller').style.visibility = 'hidden';
            }
        </script>
        <div class="button">
            <input type="submit" value="<fmt:message key="signin.register"/>">
        </div>
    </form>
</div>
</body>