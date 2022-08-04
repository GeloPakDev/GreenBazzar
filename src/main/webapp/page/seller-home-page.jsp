<%@ page import="com.example.webapplication.command.RequestParameter" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seller Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/seller-home-style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<div class="container">
    <div class="title">Greenbazaar</div>
    <div class="profile">Seller Panel</div>
    <div class="statuses">Statuses</div>
    <hr>
    <div class="sidebar">
        <div class="card-body">
            <table id="datatablesSimple">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>price</th>
                    <th>description</th>
                    <th>weight</th>
                    <th>category</th>
                    <th>quantity</th>
                    <th>approve</th>
                    <th>decline</th>
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
                        <td>${tempProduct.category}</td>
                        <td>${tempProduct.quantity}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_order_product_status" name="command">
                                <% if (request.getParameter(RequestParameter.ORDER_CHECKER) != null) { %>
                                <input type="hidden" value="${tempProduct.order}" name="order_id">
                                <input type="hidden" name="order_checker" value="order_checker">
                                <%
                                    } else {
                                        System.out.println("Order doesn't has an Id");
                                    }%>
                                <input type="hidden" name="products_id" value="${tempProduct.id}">
                                <button name="status" type="submit" value="APPROVED">
                                    Approve
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_order_product_status" name="command">
                                <input type="hidden" name="products_id" value="${tempProduct.id}">
                                <button name="status" value="DECLINED">
                                    Decline
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="vl"></div>
        <span class="products">Products</span>
        <button type="button" class="edit-btn" data-bs-toggle="modal" data-bs-target="#myModalProduct">
            <i class="fa fa-plus"></i></button>
        <div class="modal" id="myModalProduct">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" value="add_product" name="command">
                            <input name="id" type="hidden" value="${id}">
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required">Name</label>
                                    <input name="name" type="text" class="form-control" id="address-line">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required">Photo</label>
                                    <input name="photo" type="text" class="form-control" id="city">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required">Price</label>
                                    <input name="price" type="text" class="form-control" id="postal-code">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required">Description</label>
                                    <input name="description" type="text" class="form-control" id="country">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required">Category</label>
                                    <input name="category" type="text" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label required">Weight</label>
                                    <input name="weight" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="mb-3">
                                    <label class="form-label required">Quantity</label>
                                    <input name="quantity" type="text" class="form-control">
                                </div>
                            </div>
                            <input type="submit" class="btn" value="Submit">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="PENDING">
                        Pending
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="APPROVED">
                        Approved
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="DECLINED">
                        Declined
                    </button>
                </form>
            </li>
        </ul>
        <hr>
        <div>Orders</div>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_order_product_by_status" name="command">
                    <input type="hidden" name="order_checker" value="order_checker">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" type="submit" value="PENDING">
                        Pending
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_order_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="APPROVED">
                        Approved
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_order_product_by_status" name="command">
                    <input type="hidden" name="id" value="${id}">
                    <button name="status" value="DECLINED">
                        Declined
                    </button>
                </form>
            </li>
        </ul>
        <hr>
        <div>About shop</div>
    </div>
</div>
</body>
</html>