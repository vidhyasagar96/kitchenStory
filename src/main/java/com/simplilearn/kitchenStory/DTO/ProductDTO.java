package com.simplilearn.kitchenStory.DTO;

public class ProductDTO {

    private long id;
    private String name;
    private int categoryId;
    private double price;
    private String description;

    public ProductDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", price=" + price + ", description=" + description + "]";
    }

}

