package com.example.webapplication.entity.product;

import com.example.webapplication.entity.AbstractEntity;

import java.io.Serial;
import java.time.LocalDate;

public class Product implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Double price;
    private String description;
    private Double weight;
    private LocalDate created_at;
    private LocalDate modified_at;
    private LocalDate deleted_at;
    private Long category_id;
    private Long inventory_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDate modified_at) {
        this.modified_at = modified_at;
    }

    public LocalDate getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDate deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Long inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", created_at=").append(created_at);
        sb.append(", modified_at=").append(modified_at);
        sb.append(", deleted_at=").append(deleted_at);
        sb.append(", category_id=").append(category_id);
        sb.append(", inventory_id=").append(inventory_id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (weight != null ? !weight.equals(product.weight) : product.weight != null) return false;
        if (created_at != null ? !created_at.equals(product.created_at) : product.created_at != null) return false;
        if (modified_at != null ? !modified_at.equals(product.modified_at) : product.modified_at != null) return false;
        if (deleted_at != null ? !deleted_at.equals(product.deleted_at) : product.deleted_at != null) return false;
        if (category_id != null ? !category_id.equals(product.category_id) : product.category_id != null) return false;
        return inventory_id != null ? inventory_id.equals(product.inventory_id) : product.inventory_id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (modified_at != null ? modified_at.hashCode() : 0);
        result = 31 * result + (deleted_at != null ? deleted_at.hashCode() : 0);
        result = 31 * result + (category_id != null ? category_id.hashCode() : 0);
        result = 31 * result + (inventory_id != null ? inventory_id.hashCode() : 0);
        return result;
    }
}