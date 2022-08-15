<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html lang="en">
<head>
    <title>Admin About Me</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <%@include file="../components/css-js.jsp" %>
</head>
<header>
    <div class="header-main">
        <div class="container" style=" max-width: 700px;
    width: 100%;  position: absolute;
    left: 220px;">
            <span class="title" style="  position: absolute;
    top: 30px;
    font-size: 31px;
    font-weight: 500;">Greenbazaar</span>
            <span class="profile" style="position: absolute;
    font-size: 31px;
    top: 70px;
    "><fmt:message key="customermainpage.profile"/></span>
            <form action="${pageContext.request.contextPath}/controller" method="post" style="top:70px;
        left: 220px;
        position:absolute;">
                <input type="hidden" value="logout" name="command">
                <input value="<fmt:message key="seller.signout"/>" type="submit" style="color: black;
        height: 40px;
        width: 115px;
        text-decoration: none;
        border-width: 0;
        background-color: #D9D9D9;
        border-radius: 10px;">
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post" style="top: 70px;
            left : 100px;
        position:absolute;">
                <input type="hidden" value="pending_products_page" name="command">
                <input value="<fmt:message key="customer.home"/>" type="submit" style="color: black;
        height: 40px;
        width: 115px;
        text-decoration: none;
        border-width: 0;
        background-color: #D9D9D9;
        border-radius: 10px;">
            </form>
            <span class="personal-data" style=" font-size: 21px;
    padding-top: 70px;
            position:absolute;
    top: 40px;"><fmt:message key="customer.personaldata"/></span>
            <button style="position:absolute; top: 120px; left:160px;   border-width: 0;
    padding-left: 5px;
    background-color: white;" type="button" class="edit-btn"
                    data-bs-toggle="modal"
                    data-bs-target="#myModal">
                <i class="fa fa-pencil"></i></button>
            <div class="modal" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body" style="">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_admin" name="command">
                                <input name="id" type="hidden" value="${user.id}">
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="title.login"/></label>
                                    <input name="login" type="text" class="form-control" value="${user.login}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.name"/></label>
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
    <div class="input-field-label" style="padding-left: 0;
    padding-top: 7px;
    padding-bottom: 7px;
    width: 300px;">
        <label class="form-label required"><fmt:message key="title.login"/></label>
        <input type="text" value="${user.login}" required readonly style=" width: 100%;
    border-radius: 100px;
    padding-left: 20px;
    text-align: left;
    height: 50px;">
    </div>
    <div class="input-field-label" style="padding-left: 0;
    padding-top: 7px;
    padding-bottom: 7px;
    width: 300px;">
        <label class="form-label required"><fmt:message key="signin.firstname"/></label>
        <input type="text" value="${user.firstName}" required readonly style=" width: 100%;
    border-radius: 100px;
    padding-left: 20px;
    text-align: left;
    height: 50px;">
    </div>
    <div class="input-field-label" style="padding-left: 0;
    padding-top: 7px;
    padding-bottom: 7px;
    width: 300px;">
        <label class="form-label required"><fmt:message key="signin.lastname"/></label>
        <input type="text" value="${user.lastName}" required readonly style=" width: 100%;
    border-radius: 100px;
    padding-left: 20px;
    text-align: left;
    height: 50px;">
    </div>
    <div class="input-field-label" style="padding-left: 0;
    padding-top: 7px;
    padding-bottom: 7px;
    width: 300px;">
        <label class="form-label required"><fmt:message key="signin.email"/></label>
        <input type="email" value="${user.email}" required readonly style=" width: 100%;
    border-radius: 100px;
    padding-left: 20px;
    text-align: left;
    height: 50px;">
    </div>
    <div class="input-field-label" style="padding-left: 0;
    padding-top: 7px;
    padding-bottom: 7px;
    width: 300px;">
        <label class="form-label required"><fmt:message key="seller.role"/></label>
        <input type="text" value="${user.role}" required readonly style=" width: 100%;
    border-radius: 100px;
    padding-left: 20px;
    text-align: left;
    height: 50px;">
    </div>
</form>
</body>
</html>