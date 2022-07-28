<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <link href="${pageContext.request.contextPath}/css/home-style.css" rel="stylesheet">
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
                    <input type="text" name="search" placeholder="Search products">
                    <button class="favorite-btn">
                        <i class="material-icons">favorite</i>
                    </button>
                    <button class="shopping-btn">
                        <i class="material-icons">shopping_cart</i>
                    </button>
                <a style="color: black;text-decoration: none;" class="small"
                   href="${pageContext.request.contextPath}/page/customer-home-page.jsp">
                    <button class="enter-btn" type="button"> Enter</button>
                </a>
                </span>
        </div>
    </div>
</header>
<div class="row">
    <div class="categories">
        <nav>
            <ul>
                <li>
                    <a href="#">Fruits and Vegetables<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Vegetables">
                                <input type="submit" value="Vegetables" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Fruits">
                                <input type="submit" value="Fruits" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Greens">
                                <input type="submit" value="Greens" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Dried_fruits">
                                <input type="submit" value="Dried fruits" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#">Beverages<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Juices">
                                <input type="submit" value="Juices" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Water">
                                <input type="submit" value="Water" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Carbonated_drinks">
                                <input type="submit" value="Carbonated drinks" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Cold_drinks">
                                <input type="submit" value="Cold_drinks" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Energetic_drinks">
                                <input type="submit" value="Energetic drinks" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                    </ul>
                </li>
                <li><a href="#">Milk and dairy products<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Milk">
                                <input type="submit" value="Milk" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Yogurt">
                                <input type="submit" value="Yogurt" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Sour_cream">
                                <input type="submit" value="Sour cream" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Cheese_curds">
                                <input type="submit" value="Cheese curds" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Butter">
                                <input type="submit" value="Butter" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Cheese">
                                <input type="submit" value="Cheese" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Eggs">
                                <input type="submit" value="Eggs" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                    </ul>
                </li>
                <li><a href="#">Meat and meat products<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Beef">
                                <input type="submit" value="Beef" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Lamb">
                                <input type="submit" value="Lamb" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Poultry_meat">
                                <input type="submit" value="Poultry meat" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Rabbit_meat">
                                <input type="submit" value="Rabbit meat" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Fish">
                                <input type="submit" value="Fish" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command">
                                <input type="hidden" name="category" value="Sausage">
                                <input type="submit" value="Sausage" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Fish">
                                <input type="submit" value="Fish" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                    </ul>
                </li>
                <li><a href="#">Grocery<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Cereals">
                                <input type="submit" value="Cereals" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Flour">
                                <input type="submit" value="Flour" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Sugar">
                                <input type="submit" value="Sugar" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Salt">
                                <input type="submit" value="Salt" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Noodle">
                                <input type="submit" value="Noodle" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Honey">
                                <input type="submit" value="Honey" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Margarine">
                                <input type="submit" value="Margarine" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Sauces">
                                <input type="submit" value="Sauces" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Spices">
                                <input type="submit" value="Spices" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                    </ul>
                </li>
                <li><a href="#">Tea and Coffee<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Black_tea">
                                <input type="submit" value="Black tea" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Green_tea">
                                <input type="submit" value="Green tea" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Coffee">
                                <input type="submit" value="Coffee" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>

                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Cream">
                                <input type="submit" value="Cream" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                    </ul>
                </li>
                <li><a href="#">Cakes and sweets<i class="fa fa-angle-right"></i></a>
                    <ul>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Cakes">
                                <input type="submit" value="Cakes" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Pastry">
                                <input type="submit" value="Pastry" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                        <li>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="choose_by_category_product" name="command"><input
                                    type="hidden" name="category" value="Chocolate">
                                <input type="submit" value="Chocolate" class="input-field"
                                       style=" background-color:#D9D9D9;border-width: 0">
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
    <div class="banner"></div>
</div>
<div class="row">
    <span class="best-products">Best products</span>
    <span class="see-all-best">See all</span>
</div>
<div class="best d-flex flex-row flex-nowrap">
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
</div>
<div class="row">
    <span class="sales-products">Sales products</span>
    <span class="see-all-sales">See all</span>
</div>
<div class="sales d-flex flex-row flex-nowrap">
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
    <div class="card card-body">Card</div>
</div>
</body>
</html>
