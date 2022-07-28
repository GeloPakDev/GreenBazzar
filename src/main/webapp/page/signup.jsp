<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <title>Registration Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration-style.css">
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<header>
    <div class="header-main">
        <div class="container">
            <span class="title" style="font-size: 31px;font-weight: 500;position: absolute;top: 31px;left: 220px;">Greenbazaar</span>
            <span class="enter" style="font-size: 21px;font-weight: 400;position: absolute;top: 37px;right: 220px;"> <a
                    style="color: black;text-decoration: none;" class="small" href="${pageContext.request.contextPath}/page/signin.jsp">Login</a></span>
        </div>
    </div>
</header>
<div class="container">
    <div class="title">Registration</div>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" value="registration" name="command">
        <div class="user_details">
            <div class="input_box">
                <input type="text" name="login" placeholder="Login" required>
            </div>
            <div class="input_box">
                <input type="password" name="password" placeholder="Password" required>
            </div>
            <div class="input_box">
                <input type="text" name="first_name" placeholder="First Name" required>
            </div>
            <div class="input_box">
                <input type="text" name="last_name" placeholder="Last Name" required>
            </div>
        </div>

        <div class="email_input">
            <input type="text" name="email" placeholder="Email" required>
        </div>

        <div class="roles">
        <span class="custom-control custom-checkbox small">
        <input type="radio" class="custom-control-input" value="CUSTOMER" name="role" id="customer"
               onclick="roleCheck()">
        <label class="custom-control-label" for="customer">Customer</label>
        </span>

            <span class="custom-control custom-checkbox small">
        <input type="radio" class="custom-control-input" value="SELLER" name="role" id="seller" onclick="roleCheck()">
        <label class="custom-control-label" for="seller">Seller</label>
        </span>
        </div>

        <div id="ifSeller" style="visibility:hidden" class="form_control">
            <input type="text" id="company" class="form-control form-control-user" name="company"
                   placeholder="Company Name">
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
            <input type="submit" value="Register">
        </div>
    </form>
</div>
</body>