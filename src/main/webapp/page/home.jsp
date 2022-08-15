<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html lang="en">
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/home.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<header>
    <div class="header-main">
        <div class="container">
            <span class="title">Greenbazaar</span>
            <span class="header-class-actions">
                <span class="search-section" style="position: absolute;right: 270px;">
                     <form class="search" action="${pageContext.request.contextPath}/controller" method="post" style=" position: absolute;
    right: 215px;">
                       <input type="hidden" value="search_products" name="command">
                       <input type="text" placeholder="<fmt:message key="home.search"/>" name="search" style=" width: 400px;
    border-radius: 100px;
    padding-left: 20px;
    text-align: left;
    height: 40px;">
                       <button type="submit" style="background-color: white;
    border-width: 0;
    position: absolute;
    padding-top: 12px;
    padding-left: 10px;">
                           <i class="fa fa-search"></i></button>
                     </form>
                </span>

                <span class="buttons-section" style="float: right;padding-right: 180px;">
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
    <%--                    Change language--%>
                    <div class="language" style="position: absolute ; left: 100px;top:40px;">
                       <form action="${pageContext.request.contextPath}/controller" method="post">
                       <input type="hidden" value="change_language" name="command">
                       <select onchange="this.form.submit()" name="locale">
                               <option>Language</option>
                               <option value="en">English</option>
                               <option value="ru">Russian</option>
                       </select>
                       </form>
                    </div>
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
                          <button class="enter-btn" type="submit" style="color: black;
    text-decoration: none;
    right: 200px;
    top: 30px;
    position: absolute;
    border-width: 0;
    float: right;
    height: 40px;
    width: 115px;
    background-color: #D9D9D9;
    border-radius: 10px;">
                              <fmt:message key="customermainpage.profile"/>
                          </button>
                    </form>
                </span>
            </span>
        </div>
    </div>
</header>
<div class="row">
    <div class="categories">
        <nav>
            <ul>
                <li>
                    <a href="#"><fmt:message key="home.fruitsandvegetables"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Vegetables">
                                <input type="submit" value="<fmt:message key="home.vegetables"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Fruits">
                                <input type="submit" value="<fmt:message key="home.fruits"/>" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Greens">
                                <input type="submit" value="<fmt:message key="home.greens"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Dried_fruits">
                                <input type="submit" value="<fmt:message key="home.driedfruits"/>" class="input-field">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#"><fmt:message key="home.beverages"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Juices">
                                <input type="submit" value="<fmt:message key="home.juices"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Water">
                                <input type="submit" value="<fmt:message key="home.water"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Carbonated_drinks">
                                <input type="submit" value="<fmt:message key="home.carbonateddrinks"/>"
                                       class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Cold_drinks">
                                <input type="submit" value="<fmt:message key="home.coldrinks"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Energetic_drinks">
                                <input type="submit" value="<fmt:message key="home.energeticdrinks"/>"
                                       class="input-field">
                            </form>

                        </li>
                    </ul>
                </li>
                <li><a href="#"><fmt:message key="home.milkanddiary"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Milk">
                                <input type="submit" value="<fmt:message key="home.milk"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Yogurt">
                                <input type="submit" value="<fmt:message key="home.yogurt"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Sour_cream">
                                <input type="submit" value="<fmt:message key="home.sourcream"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Cheese_curds">
                                <input type="submit" value="<fmt:message key="home.cheesecruds"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Butter">
                                <input type="submit" value="<fmt:message key="home.butter"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Cheese">
                                <input type="submit" value="<fmt:message key="home.cheese"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Eggs">
                                <input type="submit" value="<fmt:message key="home.eggs"/>" class="input-field">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#"><fmt:message key="home.meat"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Beef">
                                <input type="submit" value="<fmt:message key="home.beef"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Lamb">
                                <input type="submit" value="<fmt:message key="home.lamb"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Poultry_meat">
                                <input type="submit" value="<fmt:message key="home.poultrymeat"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Rabbit_meat">
                                <input type="submit" value="<fmt:message key="home.rabbitmeat"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Fish">
                                <input type="submit" value="<fmt:message key="home.fish"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Sausage">
                                <input type="submit" value="<fmt:message key="home.sausage"/>" class="input-field">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#"><fmt:message key="home.grocery"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Cereals">
                                <input type="submit" value="<fmt:message key="home.cereals"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Flour">
                                <input type="submit" value="<fmt:message key="home.flour"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Sugar">
                                <input type="submit" value="<fmt:message key="home.sugar"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Salt">
                                <input type="submit" value="<fmt:message key="home.salt"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Noodle">
                                <input type="submit" value="<fmt:message key="home.noodle"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Honey">
                                <input type="submit" value="<fmt:message key="home.honey"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Margarine">
                                <input type="submit" value="<fmt:message key="home.margarine"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Sauces">
                                <input type="submit" value="<fmt:message key="home.sauces"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Spices">
                                <input type="submit" value="<fmt:message key="home.spices"/>" class="input-field">
                            </form>

                        </li>
                    </ul>
                </li>
                <li><a href="#"><fmt:message key="home.tea"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Black_tea">
                                <input type="submit" value="<fmt:message key="home.blacktea"/>" class="input-field">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Green_tea">
                                <input type="submit" value="<fmt:message key="home.greentea"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Coffee">
                                <input type="submit" value="<fmt:message key="home.coffee"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Cream">
                                <input type="submit" value="<fmt:message key="home.cream"/>" class="input-field">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#"><fmt:message key="home.cakesandsweets"/><i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Cakes">
                                <input type="submit" value="<fmt:message key="home.cakes"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Pastry">
                                <input type="submit" value="<fmt:message key="home.pastry"/>" class="input-field">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Chocolate">
                                <input type="submit" value="<fmt:message key="home.chocolate"/>" class="input-field">
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="banner" style="width: 55%;
    height: 210px;
    border-radius: 10px;
    background-color: mediumseagreen;"><p
            style="text-align: center; color: white ; font-size: 30px; padding-top: 70px;">Welcome to GreenBazaar!</p>
    </div>
</div>
<div class="row">
    <span class="best-products"><fmt:message key="home.topproducts"/></span>
</div>
<div class="best d-flex flex-row flex-nowrap">
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
</div>
</body>
</html>
