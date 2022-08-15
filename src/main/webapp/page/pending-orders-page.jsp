<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html>
<head>
    <title>Pending Orders Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/seller-home-style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<div class="container">
    <div class="title">Greenbazaar</div>
    <div class="profile"><fmt:message key="seller.sellerpanel"/></div>
    <div class="statuses"><fmt:message key="seller.statuses"/></div>
    <div class="language" style="position: absolute ; right: 220px;top:170px;">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" value="change_language" name="command">
            <select onchange="this.form.submit()" name="locale">
                <option>Language</option>
                <option value="en">English</option>
                <option value="ru">Russian</option>
            </select>
        </form>
    </div>
    <hr>
    <div class="sidebar">
        <div class="card-body">
            <table id="datatablesSimple" style="position: absolute;
    right: 60px;">
                <thead>
                <tr>
                    <th><fmt:message key="customer.name"/></th>
                    <th><fmt:message key="customer.photo"/></th>
                    <th><fmt:message key="customer.price"/></th>
                    <th><fmt:message key="customer.description"/></th>
                    <th><fmt:message key="customer.weight"/></th>
                    <th><fmt:message key="seller.category"/></th>
                    <th><fmt:message key="customer.quantity"/></th>
                    <th><fmt:message key="seller.approve"/></th>
                    <th><fmt:message key="seller.decline"/></th>
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
                        <td>${tempProduct.category}</td>
                        <td>${tempProduct.quantity}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_order_product_status" name="command">
                                <input type="hidden" value="${tempProduct.order}" name="order_id">
                                <input type="hidden" name="products_id" value="${tempProduct.id}">
                                <button name="status" type="submit" value="APPROVED">
                                    <fmt:message key="seller.approve"/>
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_order_product_status" name="command">
                                <input type="hidden" name="products_id" value="${tempProduct.id}">
                                <button name="status" value="DECLINED">
                                    <fmt:message key="seller.decline"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="vl"></div>
        <span class="products"><fmt:message key="seller.products"/></span>
        <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#myModalProduct">
            <i class="fa fa-plus"></i></button>
        <div class="modal" id="myModalProduct">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/controller" method="post"
                              enctype="multipart/form-data">
                            <input type="hidden" value="add_product" name="command">
                            <input name="id" type="hidden" value="${id}">
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.name"/></label>
                                    <input name="name" type="text" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.photo"/></label>
                                    <input name="photo" type="file" class="form-control" accept=".png, .jpg, .jpeg">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.price"/></label>
                                    <input name="price" type="text" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.description"/></label>
                                    <input name="description" type="text" class="form-control" id="country">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="seller.category"/></label>
                                    <input name="category" type="text" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.weight"/></label>
                                    <input name="weight" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required"><fmt:message key="customer.quantity"/></label>
                                    <input name="quantity" type="text" class="form-control">
                                </div>
                            </div>
                            <input type="submit" class="btn" value="<fmt:message key="customer.submit"/>">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="pending_seller_products" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status">
                        <fmt:message key="seller.pending"/>
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="chosen_seller_by_status_products" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="APPROVED">
                        <fmt:message key="seller.approved"/>
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="chosen_seller_by_status_products" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="DECLINED">
                        <fmt:message key="seller.declined"/>
                    </button>
                </form>
            </li>
        </ul>
        <hr>
        <div><fmt:message key="seller.orders"/></div>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="pending_seller_orders" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button type="submit">
                        <fmt:message key="seller.pending"/>

                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_order_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="APPROVED">
                        <fmt:message key="seller.approved"/>
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_order_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="DECLINED">
                        <fmt:message key="seller.declined"/>
                    </button>
                </form>
            </li>
        </ul>
        <hr>
        <div>
            <a href="${pageContext.request.contextPath}/page/seller-about-me-page.jsp">
                <input value="<fmt:message key="customermainpage.profile"/>" class="enter-btn" type="submit" style="color: black;
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
        </div>
    </div>
</div>
</body>
</html>