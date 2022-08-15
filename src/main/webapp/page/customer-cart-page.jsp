<%@ page import="com.example.webapplication.command.RequestParameter" %>
<%@ page import="com.example.webapplication.entity.product.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart page</title>
    <link href="${pageContext.request.contextPath}/style/customer-cart-style.css" rel="stylesheet">
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
                <span class="search-section">
                     <form class="search" action="${pageContext.request.contextPath}/controller" method="post">
                       <input type="hidden" value="search_products" name="command">
                       <input type="text" placeholder="<fmt:message key="home.search"/>" name="search">
                       <button type="submit">
                           <i class="fa fa-search"></i></button>
                     </form>
                </span>

                <span class="buttons-section">
<%--                    Favourites page--%>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                         <input type="hidden" value="favourite" name="command">
                         <button class="shopping-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 300px;
    top: 30px;
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
    right: 250px;
    top: 30px;
    position: absolute;
    border-width: 0;
    background-color: white;
    border-radius: 10px;">
                                    <i class="material-icons">shopping_cart</i>
                                </button>
                    </form>
<%--                    Profile page--%>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="about_me" name="command">
                                <input class="enter-btn" type="submit"
                                       value="<fmt:message key="customermainpage.profile"/> " style=" color: black;
    text-decoration: none;
    right: 130px;
    top: 30px;
    position: absolute;
    border-width: 0;
    height: 40px;
    width: 115px;
    background-color: #D9D9D9;
    border-radius: 10px;">
                    </form>
                </span>
            </span>
        </div>
    </div>
</header>
<div class="card-body">
    <%
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        if (productList.isEmpty()) {
    %>
    <p style="text-align: center;font-size: 30px;"><fmt:message key="customer.cartlist"/></p>
    <%
    } else {
    %>
    <table id="datatablesSimple">
        <thead>
        <tr>
            <th><fmt:message key="customer.name"/></th>
            <th><fmt:message key="customer.photo"/></th>
            <th><fmt:message key="customer.price"/></th>
            <th><fmt:message key="customer.description"/></th>
            <th><fmt:message key="customer.weight"/></th>
            <th><fmt:message key="customer.quantity"/></th>
            <th><fmt:message key="customer.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempProduct" items="${cart}">
            <tr>
                <td>${tempProduct.key.name}</td>
                <td><img src="data:image/jpg;base64,${tempProduct.key.photo}" width="70" height="70"/></td>
                <td>${tempProduct.key.price}</td>
                <td>${tempProduct.key.description}</td>
                <td>${tempProduct.key.weight}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="products_id" value="${tempProduct.key.id}">
                        <input type="hidden" name="product_quantity" value="${tempProduct.value}">
                        <input type="hidden" value="change_product_quantity" name="command">
                        <button type="submit" name="action" value="increase"
                                onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                            <i class="fa fa-plus"></i>
                        </button>
                        <input type="number" name="product_quantity" id="price-max" value="${tempProduct.value}" min="0"
                               readonly style="width : 50px;">
                        <button type="submit"
                                value="decrease"
                                name="action"
                                onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                            <i class="fa fa-minus"></i>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="product_quantity" value="${tempProduct.value}">
                        <input type="hidden" name="products_id" value="${tempProduct.key.id}">
                        <input type="hidden" value="delete_from_cart" name="command">
                        <input type="submit" value="<fmt:message key="customer.delete"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%
        }
    %>
</div>
<div class="cart-overview">
    <div class="total-products">
        <fmt:message key="customer.totalnumberofproducts"/>: ${total_quantity}
    </div>
    <div class="total-price">
        <fmt:message key="customer.totalpriceofproducts"/>: ${total_price}
    </div>
    <div class="proceed-to-payment">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="proceed_to_payment" name="command">
            <input type="submit" value=" <fmt:message key="customer.proceedtopayment"/>" class="checkout">
        </form>
    </div>
</div>
</body>
</html>