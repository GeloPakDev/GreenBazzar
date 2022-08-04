package com.example.webapplication.entity.order;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.user.User;

import java.io.Serial;
import java.sql.Date;

public class Order implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private User user;
    private OrderStatus orderStatus;
    private Date orderedDate;
    private Date confirmedDate;
    private Date completedDate;
    private Date canceledDate;

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Date getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(Date canceledDate) {
        this.canceledDate = canceledDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", orderedDate=").append(orderedDate);
        sb.append(", confirmedDate=").append(confirmedDate);
        sb.append(", completedDate=").append(completedDate);
        sb.append(", canceledDate=").append(canceledDate);
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
        if (orderStatus != order.orderStatus) return false;
        if (orderedDate != null ? !orderedDate.equals(order.orderedDate) : order.orderedDate != null) return false;
        if (confirmedDate != null ? !confirmedDate.equals(order.confirmedDate) : order.confirmedDate != null)
            return false;
        if (completedDate != null ? !completedDate.equals(order.completedDate) : order.completedDate != null)
            return false;
        return canceledDate != null ? canceledDate.equals(order.canceledDate) : order.canceledDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderedDate != null ? orderedDate.hashCode() : 0);
        result = 31 * result + (confirmedDate != null ? confirmedDate.hashCode() : 0);
        result = 31 * result + (completedDate != null ? completedDate.hashCode() : 0);
        result = 31 * result + (canceledDate != null ? canceledDate.hashCode() : 0);
        return result;
    }
}