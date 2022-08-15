<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product Page</title>
</head>
<body>
<div class="modal" id="myModalProduct">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/controller" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" value="update_seller_product" name="command">
                    <input name="products_id" type="hidden" value="${products_id}">
                    <div class="form-row">
                        <div class="mb-3">
                            <label class="form-label required">Name</label>
                            <input name="name" type="text" class="form-control"
                                   value="${name}"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label required">Photo</label>
                            <img src="data:image/jpg;base64,${photo}" width="70" height="70"/>
                            <input name="photo" type="file" class="form-control" accept=".png, .jpg, .jpeg"
                                   value="${photo}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="mb-3">
                            <label class="form-label required">Price</label>
                            <input name="price" type="text" class="form-control" value= ${price}>
                        </div>
                        <div class="mb-3">
                            <label class="form-label required">Description</label>
                            <input name="description" type="text" class="form-control" id="country"
                                   value= ${description}>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="mb-3">
                            <label class="form-label required">Category</label>
                            <input name="category" type="text" class="form-control" value= ${category}>
                        </div>
                        <div class="mb-3">
                            <label class="form-label required">Weight</label>
                            <input name="weight" type="text" class="form-control" value= ${weight}>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="mb-3">
                            <label class="form-label required">Quantity</label>
                            <input name="quantity" type="text" class="form-control" value=${quantity}>
                        </div>
                    </div>
                    <input type="submit" class="btn" value="Submit">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
