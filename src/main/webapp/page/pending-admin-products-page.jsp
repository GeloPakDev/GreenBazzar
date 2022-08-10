<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <div class="profile">Admin Panel</div>
  <div class="statuses">Applications</div>
  <hr>
  <div class="sidebar">
    <div class="card-body">
      <table id="datatablesSimple" style="position: absolute;right: 60px;">
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
                  Approve
                </button>
              </form>
            </td>
            <td>
              <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" value="update_seller_product_status" name="command">
                <input type="hidden" name="products_id" value="${tempProduct.id}">
                <button name="status" value="DECLINED">
                  DECLINE
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
    <ul>
      <li>
        <form action="${pageContext.request.contextPath}/controller" method="post">
          <input type="hidden" value="pending_products_page" name="command">
          <input type="hidden" name="id" value="${user.id}">
          <button name="status" value="PENDING">
            Pending
          </button>
        </form>
      </li>
    </ul>
    <hr>
    <div>History</div>
    <ul>
      <li>
        <form action="${pageContext.request.contextPath}/controller" method="post">
          <input type="hidden" value="choose_product_by_status" name="command">
          <input type="hidden" name="id" value="${user.id}">
          <button name="status" value="APPROVED">
            Approved
          </button>
        </form>
      </li>
      <li>
        <form action="${pageContext.request.contextPath}/controller" method="post">
          <input type="hidden" value="choose_product_by_status" name="command">
          <input type="hidden" name="id" value="${user.id}">
          <button name="status" value="DECLINED">
            Declined
          </button>
        </form>
      </li>
    </ul>
  </div>
</div>
</body>
</html>
