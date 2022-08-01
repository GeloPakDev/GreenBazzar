<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order confirmation page</title>
    <link href="${pageContext.request.contextPath}/css/order-confirmation-style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <%@include file="../components/css-js.jsp" %>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<header>
    <div class="header-main">
        <div class="container">
            <span class="title">Greenbazaar</span>
            <span class="header-class-actions">
                <a href="${pageContext.request.contextPath}/page/customer-home-page.jsp">
                    <button class="enter-btn" type="button">Profile</button>
                </a>
            </span>
        </div>
    </div>
</header>
<div class="addresses-list">
    <ul>
        <c:forEach var="tempAddress" items="${addresses}">
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="text"
                           value="${tempAddress.addressLine} ,${tempAddress.city} ,${tempAddress.postalCode} ,${tempAddress.country} ,${tempAddress.phoneNumber}"
                           required readonly>
                    <input type="hidden" value="delete_address" name="command">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="address_id" value="${tempAddress.id}">
                    <input type="radio" class="custom-control-input" value="${tempAddress}">
                </form>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="cards-list">
    <ul>
        <c:forEach var="tempCard" items="${cards}">
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="text" value="${tempCard.cardNumber} ,${tempCard.expirationDate}" required
                           readonly>
                    <input type="hidden" value="delete_card" name="command">
                    <input name="id" type="hidden" value="${user.id}">
                    <input name="card_id" type="hidden" value="${tempCard.id}">
                    <input type="radio" class="custom-control-input" value="${tempCard}">
                </form>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="products-list">
    <ul>
        <c:forEach var="tempProduct" items="${cart}">
            <li>
                <input type="text"
                       value="${tempProduct.key.name} , ${tempProduct.key.photo},${tempProduct.key.price}, ${tempProduct.key.description}, ${tempProduct.key.weight}"
                       required readonly>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
