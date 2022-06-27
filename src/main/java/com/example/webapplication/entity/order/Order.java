package com.example.webapplication.entity.order;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.User;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private User user;
    private Status status;
    private LocalDateTime orderedDate;
    private LocalDateTime reservedDate;
    private LocalDateTime returnedDate;
    private LocalDateTime rejectedDate;
    private List<Product> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDateTime orderedDate) {
        this.orderedDate = orderedDate;
    }

    public LocalDateTime getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDateTime reservedDate) {
        this.reservedDate = reservedDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public LocalDateTime getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(LocalDateTime rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", status=").append(status);
        sb.append(", orderedDate=").append(orderedDate);
        sb.append(", reservedDate=").append(reservedDate);
        sb.append(", returnedDate=").append(returnedDate);
        sb.append(", rejectedDate=").append(rejectedDate);
        sb.append(", productList=").append(productList);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (status != order.status) return false;
        if (orderedDate != null ? !orderedDate.equals(order.orderedDate) : order.orderedDate != null) return false;
        if (reservedDate != null ? !reservedDate.equals(order.reservedDate) : order.reservedDate != null) return false;
        if (returnedDate != null ? !returnedDate.equals(order.returnedDate) : order.returnedDate != null) return false;
        if (rejectedDate != null ? !rejectedDate.equals(order.rejectedDate) : order.rejectedDate != null) return false;
        return productList != null ? productList.equals(order.productList) : order.productList == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orderedDate != null ? orderedDate.hashCode() : 0);
        result = 31 * result + (reservedDate != null ? reservedDate.hashCode() : 0);
        result = 31 * result + (returnedDate != null ? returnedDate.hashCode() : 0);
        result = 31 * result + (rejectedDate != null ? rejectedDate.hashCode() : 0);
        result = 31 * result + (productList != null ? productList.hashCode() : 0);
        return result;
    }
}