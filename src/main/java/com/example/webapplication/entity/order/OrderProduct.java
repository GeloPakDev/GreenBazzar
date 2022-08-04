package com.example.webapplication.entity.order;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.product.Category;

import java.io.Serial;
import java.sql.Date;

public class OrderProduct implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String photo;
    private Double price;
    private String description;
    private Double weight;
    private Category category;
    private int quantity;
    private Date created_at;
    private Date modified_at;
    private Date deleted_at;
    private int sellerId;
    private int order;

    public OrderProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderProduct{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", category=").append(category);
        sb.append(", quantity=").append(quantity);
        sb.append(", created_at=").append(created_at);
        sb.append(", modified_at=").append(modified_at);
        sb.append(", deleted_at=").append(deleted_at);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", orderId=").append(order);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProduct that = (OrderProduct) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (sellerId != that.sellerId) return false;
        if (order != that.order) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (category != that.category) return false;
        if (created_at != null ? !created_at.equals(that.created_at) : that.created_at != null) return false;
        if (modified_at != null ? !modified_at.equals(that.modified_at) : that.modified_at != null) return false;
        return deleted_at != null ? deleted_at.equals(that.deleted_at) : that.deleted_at == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (modified_at != null ? modified_at.hashCode() : 0);
        result = 31 * result + (deleted_at != null ? deleted_at.hashCode() : 0);
        result = 31 * result + sellerId;
        result = 31 * result + order;
        return result;
    }
}