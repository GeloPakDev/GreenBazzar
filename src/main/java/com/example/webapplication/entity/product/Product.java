package com.example.webapplication.entity.product;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.builder.ProductBuilder;

import java.io.Serial;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product implements AbstractEntity {
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

    public Product() {
    }

    public Product(ProductBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.photo = builder.getPhoto();
        this.price = builder.getPrice();
        this.description = builder.getDescription();
        this.weight = builder.getWeight();
        this.category = builder.getCategory();
        this.quantity = builder.getQuantity();
        this.created_at = builder.getCreated_at();
        this.modified_at = builder.getModified_at();
        this.deleted_at = builder.getDeleted_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Date getCreated_at() {
        return created_at;
    }

    public Product setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public Product setModified_at(Date modified_at) {
        this.modified_at = modified_at;
        return this;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public Product setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (quantity != product.quantity) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (photo != null ? !photo.equals(product.photo) : product.photo != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        if (category != product.category) return false;
        if (created_at != null ? !created_at.equals(product.created_at) : product.created_at != null) return false;
        if (modified_at != null ? !modified_at.equals(product.modified_at) : product.modified_at != null) return false;
        return deleted_at != null ? deleted_at.equals(product.deleted_at) : product.deleted_at == null;
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
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
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
        sb.append('}');
        return sb.toString();
    }
}