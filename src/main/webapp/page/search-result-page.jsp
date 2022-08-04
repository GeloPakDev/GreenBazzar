<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result Page</title>
    <link href="${pageContext.request.contextPath}/css/search_result_style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <%@include file="../components/css-js.jsp" %>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/>
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
<div class="row">
    <div class="categories">
        <nav>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" value="choose_by_category_product" name="command">
                <select onchange="this.form.submit()" name="category">
                    <optgroup label="Vegetables and Fruits">
                        <option>Select category</option>
                        <option value="Vegetables">Vegetables</option>
                        <option value="Fruits">Fruits</option>
                        <option value="Greens">Greens</option>
                        <option value="Dried_fruits">Dried fruits</option>
                    </optgroup>
                    <optgroup label="Beverages">
                        <option value="Juices">Juices</option>
                        <option value="Water">Water</option>
                        <option value="Carbonated_drinks">Carbonated drinks</option>
                        <option value="Cold_drinks">Cold drinks</option>
                    </optgroup>
                    <optgroup label="Milk and dairy products">
                        <option value="Milk">Milk</option>
                        <option value="Yogurt">Yogurt</option>
                        <option value="Sour_cream">Sour cream</option>
                        <option value="Cheese_curds">Cheese curds</option>
                        <option value="Butter">Butter</option>
                        <option value="Cheese">Cheese</option>
                        <option value="Eggs">Eggs</option>
                    </optgroup>
                    <optgroup label="Meat and meat products">
                        <option value="Beef">Beef</option>
                        <option value="Lamb">Lamb</option>
                        <option value="Poultry_meat">Poultry meat</option>
                        <option value="Rabbit_meat">Rabbit meat</option>
                        <option value="Fish">Fish</option>
                        <option value="Sausage">Sausage</option>
                    </optgroup>
                    <optgroup label="Grocery">
                        <option value="Cereals">Cereals</option>
                        <option value="Flour">Flour</option>
                        <option value="Sugar">Sugar</option>
                        <option value="Salt">Salt</option>
                        <option value="Noodle">Noodle</option>
                        <option value="Honey">Honey</option>
                        <option value="Margarine">Margarine</option>
                        <option value="Sauces">Sauces</option>
                        <option value="Spices">Spices</option>
                    </optgroup>
                    <optgroup label="Tea and Coffee">
                        <option value="Black_tea">Black tea</option>
                        <option value="Green_tea">Green tea</option>
                        <option value="Coffee">Coffee</option>
                        <option value="Cream">Cream</option>
                    </optgroup>
                    <optgroup label="Cakes and sweets">
                        <option value="Cakes">Cakes</option>
                        <option value="Pastry">Pastry</option>
                        <option value="Chocolate">Chocolate</option>
                    </optgroup>
                </select>
            </form>
        </nav>
    </div>
</div>
<div class="price-range">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" value="choose_by_price_range" name="command">
        <div data-role="rangeslider">
            <label for="price-min">From:</label>
            <input type="number" name="price-from" id="price-min" value="200" min="0" max="1000">
            <label for="price-max">To:</label>
            <input type="number" name="price-to" id="price-max" value="800" min="0" max="1000">
        </div>
        <input type="submit" data-inline="true" value="Filter" class="filter">
    </form>
</div>
<div class="search-result">By "${search}" was found: ${quantity} results</div>
<div class="card-body">
    <table id="datatablesSimple">
        <thead>
        <tr>
            <th>Name</th>
            <th>Photo</th>
            <th>price</th>
            <th>description</th>
            <th>weight</th>
            <th>Cart</th>
            <th>favorites</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempProduct" items="${products}">
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
                        <input type="hidden" value="decline_product" name="command">
                        <input type="hidden" name="id" value="${user.id}">
                        <button name="orderStatus" value="DECLINED">
                            Add to favourites
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
