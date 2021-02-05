package com.example.searchengine.DTO.Validation;

public class SearchValidation {

    private String category;
    private Integer order;
    private Boolean freeShipping;
    private Integer id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getCategory() + " | " + this.getFreeShipping() + " | " + this.getOrder();
    }
}
