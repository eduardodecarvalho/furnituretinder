package com.furniturecombiner.model;

import java.math.BigDecimal;

public class Item {

    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private String picture;

    public Item() {
    }

    public Item(Long id, String name, String category, BigDecimal price, String picture) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.picture = picture;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
