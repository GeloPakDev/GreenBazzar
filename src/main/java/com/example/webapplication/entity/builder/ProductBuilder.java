package com.example.webapplication.entity.builder;

import com.example.webapplication.entity.product.Category;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;

import java.sql.Date;

public class ProductBuilder {
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
    private Status status;
    private int sellerId;

    public Product build() {
        return new Product(this);
    }

    public int getId() {
        return id;
    }

    public ProductBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public ProductBuilder setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public ProductBuilder setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ProductBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public ProductBuilder setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public ProductBuilder setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public ProductBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public int getSellerId() {
        return sellerId;
    }

    public ProductBuilder setSellerId(int sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBuilder that = (ProductBuilder) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (sellerId != that.sellerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (category != that.category) return false;
        if (created_at != null ? !created_at.equals(that.created_at) : that.created_at != null) return false;
        if (modified_at != null ? !modified_at.equals(that.modified_at) : that.modified_at != null) return false;
        return status == that.status;
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
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + sellerId;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductBuilder{");
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
        sb.append(", status=").append(status);
        sb.append(", sellerId=").append(sellerId);
        sb.append('}');
        return sb.toString();
    }
}