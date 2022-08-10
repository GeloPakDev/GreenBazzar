<%@ page import="com.example.webapplication.command.RequestParameter" %>
<%@ page import="com.example.webapplication.entity.user.Address" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webapplication.entity.user.Card" %>
<%@ page import="com.example.webapplication.command.customer.AddCardCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order confirmation page</title>
    <link href="${pageContext.request.contextPath}/style/order-confirmation-style.css" rel="stylesheet">
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
               <a href="${pageContext.request.contextPath}/page/home.jsp">
               <input value="home" class="enter-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 220px;
    top: 30px;
    position: absolute;
    border-width: 0;
    float: right;
    height: 40px;
    width: 115px;
    background-color: #D9D9D9;
    border-radius: 10px;">
               </a>
            </span>
        </div>
    </div>
</header>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" value="checkout" name="command">
    <div class="address">
        <div>Choose address from following:</div>
        <div class="addresses-list">
            <%
                List<Address> addresses = (List<Address>) session.getAttribute(RequestParameter.ADDRESSES);
                if (addresses.isEmpty()) {
            %>
            <p>Your cart is empty!</p>
            <%
            } else {
            %>
            <ul style="padding: 0; margin: 0;">
                <c:forEach var="tempAddress" items="${addresses}">
                    <li>
                        <input class="address-input" type="text"
                               value="${tempAddress.addressLine} ,${tempAddress.city} ,${tempAddress.postalCode} ,${tempAddress.country} ,${tempAddress.phoneNumber}"
                               required readonly>
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="address_id" value="${tempAddress.id}">
                        <input name="address" type="radio" value="${tempAddress}" class="radio-btn">
                    </li>
                </c:forEach>
            </ul>
            <%
                }
            %>
        </div>
    </div>
    <div class="cards">
        <div>Choose card from the following:</div>
        <div class="cards-list">
            <%
                List<Card> cards = (List<Card>) session.getAttribute(RequestParameter.CARDS);
                if (cards.isEmpty()) {
            %>
            <p>You don't have any cards!</p>
            <%
            } else {
            %>
            <ul style="padding: 0; margin: 0;">
                <c:forEach var="tempCard" items="${cards}">
                    <li>
                        <input class="card-input" type="text" value="${tempCard.cardNumber} ,${tempCard.expirationDate}"
                               required readonly>
                        <input name="card_id" type="hidden" value="${tempCard.id}">
                        <input name="card-balance" type="radio" value="${tempCard.balance}" class="radio-btn">
                    </li>
                </c:forEach>
            </ul>
            <%
                }
            %>
        </div>
    </div>
    <div class="products">
        <div>List of products:</div>
        <div class="products-list">
            <ul style="padding: 0; margin: 0;">
                <c:forEach var="tempProduct" items="${cart}">
                    <li>
                        <input class="product-input" type="text"
                               value="${tempProduct.key.name} , ${tempProduct.key.photo},${tempProduct.key.price}, ${tempProduct.key.description}, ${tempProduct.key.weight}"
                               required readonly>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="payment">
        <div class="title">Payment Data</div>
        <div class="total-price">
            Total price for payment: ${total_price}
        </div>

        <div class="proceed-to-payment">
            <input type="submit" value="Proceed to payment" class="payment-button">
        </div>
    </div>
</form>
</body>
</html>
