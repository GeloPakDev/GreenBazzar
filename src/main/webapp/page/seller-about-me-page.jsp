<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="profile">Profile</div>
            <span class="personal-data">Personal data</span>

            <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#myModal">
                <i class="fa fa-pencil"></i></button>

            <div class="modal" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body" style="height: 600px;">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_seller" name="command">
                                <input name="id" type="hidden" value="${user.id}">
                                <div class="mb-3">
                                    <label class="form-label required">Login</label>
                                    <input name="login" type="text" class="form-control" value="${user.login}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required">Name</label>
                                    <input name="first_name" type="text" class="form-control" value="${user.firstName}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required">Surname</label>
                                    <input name="last_name" type="text" class="form-control" value="${user.lastName}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required">Email</label>
                                    <input name="email" type="email" class="form-control" value="${user.email}">
                                </div>
                                <input type="submit" class="btn" value="Submit">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<body>
<form>
    <div class="input-field-label">
        <label class="form-label required">Login</label>
        <input type="text" value="${user.login}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required">First name</label>
        <input type="text" value="${user.firstName}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required">Last name</label>
        <input type="text" value="${user.lastName}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required">email</label>
        <input type="email" value="${user.email}" required readonly>
    </div>
    <div class="input-field-label">
        <label class="form-label required">role</label>
        <input type="text" value="${user.role}" required readonly>
    </div>
</form>
</body>
</html>