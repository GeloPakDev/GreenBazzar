<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html>
<head>
    <title>Search Result Page</title>
    <link href="${pageContext.request.contextPath}/style/search_result_style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <%@include file="../components/css-js.jsp" %>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>

<header style="padding-top: 31px;padding-bottom: 32px;">
    <div class="header-main">
        <div class="container">
            <span class="title">Greenbazaar</span>
            <span class="header-class-actions">
                <span class="search-section" style="position: absolute;right: 450px;">
                     <form class="search" action="${pageContext.request.contextPath}/controller" method="post">
                       <input type="hidden" value="search_products" name="command">
                       <input type="text" placeholder="<fmt:message key="home.search"/>" name="search"
                              style="width: 300px;border-radius: 100px;padding-left: 20px;text-align: left;height: 40px;">
                       <button type="submit"
                               style=" background-color:white ;color:black;border-width: 0;position: absolute;padding-top: 12px;padding-left: 10px;">
                           <i class="fa fa-search"></i></button>
                     </form>
                </span>

                <span class="buttons-section" style="float: right;padding-right: 200px;">
    <%--                    Favourites page--%>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                       <input type="hidden" value="favourite" name="command">
                       <button class="shopping-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 400px;
    top: 40px;
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
    right: 350px;
    top: 40px;
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
                          <input value="<fmt:message key="customermainpage.profile"/>" class="enter-btn" type="submit"
                                 style="color: black;
                                 text-decoration: none;
                                 right: 180px;
                                 top: 30px;
                                 position: absolute;
                                 border-width: 0;
                                 float: right;
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
<div class="row">
    <div class="categories">
        <nav>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" value="choose_by_category_product" name="command">
                <select onchange="this.form.submit()" name="category">
                    <optgroup label="<fmt:message key="home.fruitsandvegetables"/>">
                        <option>Select category</option>
                        <option value="Vegetables"><fmt:message key="home.vegetables"/></option>
                        <option value="Fruits"><fmt:message key="home.fruits"/></option>
                        <option value="Greens"><fmt:message key="home.greens"/></option>
                        <option value="Dried_fruits"><fmt:message key="home.driedfruits"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="home.beverages"/>">
                        <option value="Juices"><fmt:message key="home.juices"/></option>
                        <option value="Water"><fmt:message key="home.water"/></option>
                        <option value="Carbonated_drinks"><fmt:message key="home.carbonateddrinks"/></option>
                        <option value="Cold_drinks"><fmt:message key="home.coldrinks"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="home.milkanddiary"/>">
                        <option value="Milk"><fmt:message key="home.milk"/></option>
                        <option value="Yogurt"><fmt:message key="home.yogurt"/></option>
                        <option value="Sour_cream"><fmt:message key="home.sourcream"/></option>
                        <option value="Cheese_curds"><fmt:message key="home.cheesecruds"/></option>
                        <option value="Butter"><fmt:message key="home.butter"/></option>
                        <option value="Cheese"><fmt:message key="home.cheese"/></option>
                        <option value="Eggs"><fmt:message key="home.eggs"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="home.meat"/>">
                        <option value="Beef"><fmt:message key="home.beef"/></option>
                        <option value="Lamb"><fmt:message key="home.lamb"/></option>
                        <option value="Poultry_meat"><fmt:message key="home.poultrymeat"/></option>
                        <option value="Rabbit_meat"><fmt:message key="home.rabbitmeat"/></option>
                        <option value="Fish"><fmt:message key="home.fish"/></option>
                        <option value="Sausage"><fmt:message key="home.sausage"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="home.grocery"/>">
                        <option value="Cereals"><fmt:message key="home.cereals"/></option>
                        <option value="Flour"><fmt:message key="home.flour"/></option>
                        <option value="Sugar"><fmt:message key="home.sugar"/></option>
                        <option value="Salt"><fmt:message key="home.salt"/></option>
                        <option value="Noodle"><fmt:message key="home.noodle"/></option>
                        <option value="Honey"><fmt:message key="home.honey"/></option>
                        <option value="Margarine"><fmt:message key="home.margarine"/></option>
                        <option value="Sauces"><fmt:message key="home.sausage"/></option>
                        <option value="Spices"><fmt:message key="home.spices"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="home.tea"/>">
                        <option value="Black_tea"><fmt:message key="home.blacktea"/></option>
                        <option value="Green_tea"><fmt:message key="home.greentea"/></option>
                        <option value="Coffee"><fmt:message key="home.coffee"/></option>
                        <option value="Cream"><fmt:message key="home.cream"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="home.cakesandsweets"/>">
                        <option value="Cakes"><fmt:message key="home.cakes"/></option>
                        <option value="Pastry"><fmt:message key="home.pastry"/></option>
                        <option value="Chocolate"><fmt:message key="home.chocolate"/></option>
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
            <label for="price-min"><fmt:message key="customer.from"/></label>
            <input type="number" name="price-from" id="price-min" value="0" min="0" style="width:100px;">

            <label for="price-max"><fmt:message key="customer.to"/></label>
            <input type="number" name="price-to" id="price-max" value="0" min="0" style="width:100px;">
        </div>
        <input type="submit" data-inline="true" value="<fmt:message key="customer.filter"/>" class="filter">
    </form>
</div>
<div class="search-result"><fmt:message key="customer.by"/> "${search}" <fmt:message
        key="customer.wasfound"/>: ${quantity} <fmt:message key="customer.results"/>
</div>
<div class="card-body">
    <table id="datatablesSimple">
        <thead>
        <tr>
            <th><fmt:message key="customer.name"/></th>
            <th><fmt:message key="customer.photo"/></th>
            <th><fmt:message key="customer.price"/></th>
            <th><fmt:message key="customer.description"/></th>
            <th><fmt:message key="customer.weight"/></th>
            <th><fmt:message key="customer.cart"/></th>
            <th><fmt:message key="customer.favourites"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempProduct" items="${products}">
            <tr>
                <td>${tempProduct.name}</td>
                <td><img src="data:image/jpg;base64,${tempProduct.photo}" width="70" height="70"/></td>
                <td>${tempProduct.price}</td>
                <td>${tempProduct.description}</td>
                <td>${tempProduct.weight}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="add_to_cart" name="command">
                        <input type="hidden" name="products_id" value="${tempProduct.id}">
                        <button type="submit">
                            <fmt:message key="customer.addtocart"/>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" value="add_to_favourites" name="command">
                        <input type="hidden" name="products_id" value="${tempProduct.id}">
                        <button type="submit">
                            <fmt:message key="customer.addtofavourites"/>
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
