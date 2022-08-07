<%@ page import="com.example.webapplication.entity.order.Order" %>
<%@ page import="com.example.webapplication.entity.product.Product" %>
<%@ page import="com.example.webapplication.command.RequestParameter" %>
<%@ page import="com.example.webapplication.entity.order.OrderStatus" %>
<%@ page import="com.example.webapplication.entity.product.Status" %>
<%@ page import="com.example.webapplication.service.OrderService" %>
<%@ page import="com.example.webapplication.service.impl.OrderServiceImpl" %>
<%@ page import="com.example.webapplication.exception.ServiceException" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>About me page</title>
    <link href="${pageContext.request.contextPath}/style/about-me-style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
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

    <div class="buttons-section">
        <%--                    Favourites page--%>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="favourite" name="command">
            <button class="shopping-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 150px;
    top: 10px;
    position: absolute;
    border-width: 0;
    background-color: white;
    border-radius: 10px;">
                <i class="material-icons">favorite</i>
            </button>
        </form>
        <%--                    Bucket page--%>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="customer_bucket" name="command">
            <button class="shopping-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 200px;
    top: 10px;
    position: absolute;
    border-width: 0;
    background-color: white;
    border-radius: 10px;">
                <i class="material-icons">shopping_cart</i>
            </button>
        </form>
        <%--                    Home page--%>
        <a href="${pageContext.request.contextPath}/page/home.jsp">
            <input value="home" class="enter-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 0;
    top: 0;
    position: absolute;
    border-width: 0;
    float: right;
    height: 40px;
    width: 115px;
    background-color: #D9D9D9;
    border-radius: 10px;">
        </a>
    </div>
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
                        <input class="address-input" type="text"
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
                        <input type="hidden" value="${user.id}" name="id">
                        <div class="card_number" id="card-container">
                            <label class="form-label required">Card Number</label>
                            <input name="card-number" type="text" class="input" id="card"
                                   onkeypress='return formats(this,event)' onkeyup="return numberValidation(event)"
                                   placeholder="0000 0000 0000 0000"
                                   style="padding-top: 7px;padding-bottom: 7px;width: calc(100% / 2 - 10px);border-radius: 10px;padding-left: 20px;text-align: left;height: 30px;">
                        </div>
                        <div class="card_grp">
                            <div class="expiry_date">
                                <label class="form-label required">Expiration date</label>
                                <input name="expiration-date" type="text" class="expiry_input" data-mask="00"
                                       maxlength="5"
                                       placeholder="MM/YY"
                                       onkeypress='return formatString(this,event)'
                                       style="padding-top: 7px;padding-bottom: 7px;width: calc(100% / 2 - 10px);border-radius: 10px;padding-left: 20px;text-align: left;height: 30px;">
                            </div>
                            <div class="cvc">
                                <label class="form-label required">CVV</label>
                                <input name="cvv-number" type="text" class="cvc_input" data-mask="000"
                                       placeholder="Enter CVV" maxlength="3"
                                       onkeyup="return numberValidation(event)"
                                       style="padding-top: 7px;padding-bottom: 7px;width: calc(100% / 2 - 10px);border-radius: 10px;padding-left: 20px;text-align: left;height: 30px;">
                            </div>
                            <div class="card-balance">
                                <label class="form-label required">Balance</label>
                                <input name="card-balance" type="text" class="card_balance" data-mask="000"
                                       placeholder="Enter balance"
                                       onkeyup="return numberValidation(event)"
                                       maxlength="8"
                                       style="padding-top: 7px;padding-bottom: 7px;width: calc(100% / 2 - 10px);border-radius: 10px;padding-left: 20px;text-align: left;height: 30px;">
                            </div>
                        </div>
                        <input type="submit" class="btn">
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
                        <input class="card-input" type="text" value="${tempCard.cardNumber} ,${tempCard.expirationDate}"
                               required readonly>
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

    <div class="user-orders">Orders</div>

    <div class="orders-list">
        <ul style="padding: 0; margin: 0;">
            <%
                OrderService orderService = OrderServiceImpl.getInstance();
                //Get all the user's orders
                HashMap<Order, List<Product>> orders = (HashMap<Order, List<Product>>) session.getAttribute(RequestParameter.ORDERS);
                //Get list of orders
                List<Order> orderList = new ArrayList<>(orders.keySet());
                //Get list of products to each corresponding order
                List<List<Product>> products = new ArrayList<>(orders.values());
                for (int i = 0; i < orders.keySet().size(); i++) {
                    int orderID = orderList.get(i).getId();
                    OrderStatus orderStatus = orderList.get(i).getOrderStatus();
            %>
            <p>Order <%=orderID%> Status: <%=orderStatus%>
            </p>
            <%
                String status = "";
                int p = products.get(i).size();
                List<String> statusesOfProducts = new ArrayList<>();
                for (int j = 0; j < p; j++) {
                    //get products from the list
                    Product product = products.get(i).get(j);
                    int productID = product.getId();
                    try {
                        //Get status of the product in the order
                        Optional<String> optionalStatus = orderService.findOrderProductStatus(orderID, productID);
                        if (optionalStatus.isPresent()) {
                            status = optionalStatus.get();
                        }
                    } catch (ServiceException e) {
                        throw new RuntimeException(e);
                    }
                    statusesOfProducts.add(status);
            %>
            <li>
                <input class="order-input" type="text"
                       value="<%=product.getName()%>  status : <%=status%>"
                       required readonly>
            </li>
            <%
                    }
//                    int check = 0;
//                    for (String productStatus : statusesOfProducts) {
//                        if (!Objects.equals(productStatus, String.valueOf(Status.APPROVED))) {
//                            check++;
//                        } else if (Objects.equals(productStatus, String.valueOf(Status.DECLINED))) {
//                            check = -10;
//                            break;
//                        }
//                    }
//                    if (check == 0) {
//                        try {
//                            orderService.updateOrderStatus(orderID, String.valueOf(Status.APPROVED));
//                        } catch (ServiceException e) {
//                            throw new RuntimeException(e);
//                        }
//                    } else if (check == -10) {
//                        try {
//                            orderService.updateOrderStatus(orderID , String.valueOf(Status.DECLINED));
//                        } catch (ServiceException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
                }
            %>
        </ul>
    </div>
</div>
</body>
</html>

<script>
    function formats(ele, e) {
        if (ele.value.length < 19) {
            ele.value = ele.value.replace(/\W/gi, '').replace(/(.{4})/g, '$1 ');
            return true;
        } else {
            return false;
        }
    }

    function numberValidation(e) {
        e.target.value = e.target.value.replace(/[^\d ]/g, '');
        return false;
    }
</script>


<script>
    function formatString(e) {
        var inputChar = String.fromCharCode(event.keyCode);
        var code = event.keyCode;
        var allowedKeys = [8];
        if (allowedKeys.indexOf(code) !== -1) {
            return;
        }

        event.target.value = event.target.value.replace(
            /^([1-9]\/|[2-9])$/g, '0$1/' // 3 > 03/
        ).replace(
            /^(0[1-9]|1[0-2])$/g, '$1/' // 11 > 11/
        ).replace(
            /^([0-1])([3-9])$/g, '0$1/$2' // 13 > 01/3
        ).replace(
            /^(0?[1-9]|1[0-2])([0-9]{2})$/g, '$1/$2' // 141 > 01/41
        ).replace(
            /^([0]+)\/|[0]+$/g, '0' // 0/ > 0 and 00 > 0
        ).replace(
            /[^\d\/]|^[\/]*$/g, '' // To allow only digits and `/`
        ).replace(
            /\/\//g, '/' // Prevent entering more than 1 `/`
        );
    }
</script>