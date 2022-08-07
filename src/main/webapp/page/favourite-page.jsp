<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.webapplication.entity.product.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.example.webapplication.command.RequestParameter" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Favourite page</title>
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

                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="customer_bucket" name="command">
                                <button class="shopping-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 130px;
    top: 30px;
    position: absolute;
    border-width: 0;
    height: 40px;
    width: 115px;
    background-color: #D9D9D9;
    border-radius: 10px;">
                                    <i class="material-icons">shopping_cart</i>
                                </button>
                    </form>

                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="about_me" name="command">
                                <input type="hidden" name="category" value="Vegetables">
                                <input class="enter-btn" type="submit" value="profile" style=" color: black;
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
        List<Product> favouriteList = (List<Product>) session.getAttribute(RequestParameter.FAVOURITE_LIST);
        if (favouriteList.isEmpty()) {
    %>
    <p>Favourite list is empty!</p>
    <%
    } else {
    %>
    <table id="datatablesSimple">
        <thead>
        <tr>
            <th>Name</th>
            <th>Photo</th>
            <th>price</th>
            <th>description</th>
            <th>weight</th>
            <th>Quantity</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempProduct" items="${favourite_list}">
            <tr>
                <td>${tempProduct.name}</td>
                <td>${tempProduct.photo}</td>
                <td>${tempProduct.price}</td>
                <td>${tempProduct.description}</td>
                <td>${tempProduct.weight}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="add_to_cart" name="command">
                        <input type="hidden" name="products_id" value="${tempProduct.id}">
                        <button type="submit" name="orderStatus">
                            Add To cart
                        </button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="delete_from_favourites" name="command">
                        <input type="hidden" name="products_id" value="${tempProduct.id}">
                        <input type="submit" value="delete">
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
</body>
</html>
