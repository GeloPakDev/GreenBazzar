<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart page</title>
    <link href="${pageContext.request.contextPath}/customer-cart-style.css" rel="stylesheet">
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
                       <input type="text" placeholder="Search.." name="search">
                       <button type="submit">
                           <i class="fa fa-search"></i></button>
                     </form>
                </span>

                <span class="buttons-section">
                <button class="favorite-btn">
                    <i class="material-icons">favorite</i>
                </button>

                    <a href="${pageContext.request.contextPath}/page/customer-cart-page.jsp">
                        <button class="shopping-btn" type="button">
                            <i class="material-icons">shopping_cart</i>
                        </button>
                    </a>

                <a href="${pageContext.request.contextPath}/page/customer-home-page.jsp">
                    <button class="enter-btn" type="button">Profile</button>
                </a>
                </span>
            </span>
        </div>
    </div>
</header>
<div class="card-body">
    <table id="datatablesSimple" style="position: absolute;right: 220px;">
        <thead>
        <tr>
            <th>Name</th>
            <th>Photo</th>
            <th>price</th>
            <th>description</th>
            <th>weight</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempProduct" items="${cart}">
            <tr>
                <td>${tempProduct.key.name}</td>
                <td>${tempProduct.key.photo}</td>
                <td>${tempProduct.key.price}</td>
                <td>${tempProduct.key.description}</td>
                <td>${tempProduct.key.weight}</td>
                <td>
                    <input type="number" name="product_quantity" id="price-max" value="${tempProduct.value}" min="0"
                           max="1000"></td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="product_quantity" value="${tempProduct.value}">
                        <input type="hidden" name="products_id" value="${tempProduct.key.id}">
                        <input type="hidden" value="delete_from_cart" name="command">
                        <input type="submit" value="delete"></input>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="total-products">
    Total number of products:${total_quantity}
</div>
<div class="total-price">
    Total price of products:${total_price}
</div>
<div class="proceed-to-payment">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" value="proceed_to_payment" name="command">
        <input type="submit" value="Proceed to payment">
    </form>
</div>
</body>
</html>