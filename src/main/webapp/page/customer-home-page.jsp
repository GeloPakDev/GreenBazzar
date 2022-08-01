<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>About me page</title>
    <link href="${pageContext.request.contextPath}/css/about-me-style.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<div class="container">
    <div class="title">Greenbazaar</div>
    <div class="profile">Profile</div>
    <span class="personal-data">Personal data</span>

    <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#myModal">
        <i class="fa fa-pencil"></i></button>

    <a style="color: black;text-decoration: none;" class="small"
       href="${pageContext.request.contextPath}/page/home.jsp">
        <button class="enter-btn" type="button">home</button>
    </a>
    <!--Modal page for changing user data-->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="update_customer" name="command">
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
    <!--User Data -->
    <form>
        <div class="input-field">
            <input type="text" value="${user.login}" required readonly>
        </div>
        <div class="input-field">
            <input type="text" value="${user.firstName}" required readonly>
        </div>
        <div class="input-field">
            <input type="text" value="${user.lastName}" required readonly>
        </div>
        <div class="input-field">
            <input type="email" value="${user.email}" required readonly>
        </div>
        <div class="input-field">
            <input type="text" value="${user.role}" required readonly>
        </div>
    </form>
    <!--User Addresses-->
    <span class="address-title">Address</span>
    <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#myModalAddress">
        <i class="fa fa-plus" aria-hidden="true"></i></button>
    <!--Modal for adding User address-->
    <div class="modal" id="myModalAddress">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="add_address" name="command">
                        <input name="id" type="hidden" value="${user.id}">
                        <div class="form-row">
                            <div class="mb-3">
                                <label class="form-label required">Address</label>
                                <input name="address-line" type="text" class="form-control" id="address-line">
                            </div>
                            <div class="mb-3">
                                <label class="form-label required">City</label>
                                <input name="city" type="text" class="form-control" id="city">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="mb-3">
                                <label class="form-label required">Postal Code</label>
                                <input name="postal-code" type="text" class="form-control" id="postal-code">
                            </div>
                            <div class="mb-3">
                                <label class="form-label required">Country</label>
                                <input name="country" type="text" class="form-control" id="country">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="mb-3">
                                <label class="form-label required">Phone number</label>
                                <input name="phone-number" type="text" class="form-control" id="phone-number">
                            </div>
                        </div>
                        <input type="submit" class="btn" value="Submit">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--List of user addresses-->
    <div class="addresses-list">
        <ul style="padding: 0; margin: 0;">
            <c:forEach var="tempAddress" items="${addresses}">
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="text"
                               value="${tempAddress.addressLine} ,${tempAddress.city} ,${tempAddress.postalCode} ,${tempAddress.country} ,${tempAddress.phoneNumber}"
                               required readonly>
                        <input type="hidden" value="delete_address" name="command">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="address_id" value="${tempAddress.id}">
                        <button style="position:absolute; padding-left:5px; padding-top: 15px;" type="submit"
                                class="edit-btn">
                            <i class="fa fa-trash" aria-hidden="true"></i></button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!--User Banking Cards-->
    <span class="banking-cards-title">Banking Cards</span>
    <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#myModalBankingCards">
        <i class="fa fa-plus" aria-hidden="true"></i></button>
    <!--Modal for the Banking Card-->
    <div class="modal" id="myModalBankingCards">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <form class="credit-card" action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="add_card" name="command">
                        <input name="id" type="hidden" value="${user.id}">
                        <div class="card_number" id="card-container">
                            <input name="card-number" type="text" class="input" id="card"
                                   placeholder="0000 0000 0000 0000">
                            <div id="logo"></div>
                        </div>
                        <div class="card_grp">
                            <div class="expiry_date">
                                <input name="expiration-date" type="text" class="expiry_input" data-mask="00"
                                       placeholder="00">
                            </div>
                            <div class="cvc">
                                <input name="cvv-number" type="text" class="cvc_input" data-mask="000"
                                       placeholder="00">
                            </div>
                            <div>
                                <input name="balance" type="text" class="card_balance" data-mask="000"
                                       placeholder="00">
                            </div>
                        </div>
                        <input type="submit" class="btn" value="Add">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- List of BankingCards-->
    <div class="cards-list">
        <ul style="padding: 0; margin: 0;">
            <c:forEach var="tempCard" items="${cards}">
                <li>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="text" value="${tempCard.cardNumber} ,${tempCard.expirationDate}" required
                               readonly>
                        <input type="hidden" value="delete_card" name="command">
                        <input name="id" type="hidden" value="${user.id}">
                        <input name="card_id" type="hidden" value="${tempCard.id}">
                        <button style="position:absolute; padding-left:5px; padding-top: 15px;" type="submit"
                                class="edit-btn">
                            <i class="fa fa-trash" aria-hidden="true"></i></button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>