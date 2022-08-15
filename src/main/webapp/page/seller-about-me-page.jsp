<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html>
<head>
    <title>Seller About Me</title>
    <link href="${pageContext.request.contextPath}/style/seller-about-me-style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <%@include file="../components/css-js.jsp" %>
</head>
<header>
    <div class="header-main">
        <div class="container">
            <span class="title">Greenbazaar</span>
            <span class="profile" style="position: absolute;
    font-size: 31px;"><fmt:message key="customermainpage.profile"/></span>

            <form action="${pageContext.request.contextPath}/controller" method="post"
                  style="top: 70px;
            left : 120px;
        position:absolute;">
                <input type="hidden" value="pending_seller_products" name="command">
                <input value="<fmt:message key="customer.home"/>" type="submit" style="color: black;
        height: 40px;
        width: 115px;
        text-decoration: none;
        border-width: 0;
        background-color: #D9D9D9;
        border-radius: 10px;">
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post"
                  style="top:70px;
                  left: 240px;
                  position:absolute;"
            >
                <input type="hidden" value="logout" name="command">
                <input value="<fmt:message key="seller.signout"/>" type="submit" style="color: black;
        height: 40px;
        width: 115px;
        text-decoration: none;
        border-width: 0;
        background-color: #D9D9D9;
        border-radius: 10px;">
            </form>

            <span class="personal-data" style=" font-size: 21px;
            position:absolute;
    top: 40px;"><fmt:message key="customer.personaldata"/></span>
            <button style="position:absolute; top: 120px; left:160px;" type="button" class="edit-btn"
                    data-bs-toggle="modal" data-bs-target="#myModal">
                <i class="fa fa-pencil"></i></button>

            <div class="modal" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_seller" name="command">
                                <input name="id" type="hidden" value="${user.id}">
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="title.login"/></label>
                                    <input name="login" type="text" class="form-control" value="${user.login}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="signin.firstname"/></label>
                                    <input name="first_name" type="text" class="form-control" value="${user.firstName}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="signin.lastname"/></label>
                                    <input name="last_name" type="text" class="form-control" value="${user.lastName}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="signin.email"/></label>
                                    <input name="email" type="email" class="form-control" value="${user.email}">
                                </div>
                                <input type="submit" class="btn" value="<fmt:message key="customer.submit"/>">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<body>
<form style="position: absolute;
    left: 230px;
    top: 170px;">
    <div class="input-field-label">
        <label class="form-label required"><fmt:message key="title.login"/></label>
        <input type="text" value="${user.login}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required"><fmt:message key="signin.firstname"/></label>
        <input type="text" value="${user.firstName}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required"><fmt:message key="signin.lastname"/></label>
        <input type="text" value="${user.lastName}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required"><fmt:message key="signin.email"/></label>
        <input type="email" value="${user.email}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required"><fmt:message key="seller.role"/></label>
        <input type="text" value="${user.role}" required readonly>
    </div>
</form>
</body>
</html>