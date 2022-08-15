<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.language"/>
<html>
<head>
    <title>Pending Products</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/seller-home-style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <%@include file="../components/css-js.jsp" %>
</head>
<body>
<div class="container">
    <div class="title">Greenbazaar</div>
    <div class="profile"><fmt:message key="admin.adminpanel"/></div>
    <div class="statuses"><fmt:message key="admin.applications"/></div>
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
            <table id="datatablesSimple" style="position: absolute;right: 60px;">
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
                                <input type="hidden" value="update_seller_product_status" name="command">
                                <input type="hidden" name="products_id" value="${tempProduct.id}">
                                <button name="status" value="APPROVED">
                                    <fmt:message key="seller.approve"/>
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" value="update_seller_product_status" name="command">
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
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="pending_products_page" name="command">
                    <input type="hidden" name="id" value="${user.id}">
                    <button name="status" value="PENDING">
                        <fmt:message key="seller.pending"/>
                    </button>
                </form>
            </li>
        </ul>
        <hr>
        <div><fmt:message key="admin.history"/>
        </div>
        <ul>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_product_by_status" name="command">
                    <input type="hidden" name="id" value="${user.id}">
                    <button name="status" value="APPROVED">
                        <fmt:message key="seller.approved"/>
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" value="choose_product_by_status" name="command">
                    <input type="hidden" name="id" value="${user.id}">
                    <button name="status" value="DECLINED">
                        <fmt:message key="seller.declined"/>
                    </button>
                </form>
            </li>
        </ul>
        <hr>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/page/admin-about-me-page.jsp">
                    <button>
                        <fmt:message key="admin.aboutme"/>
                    </button>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
