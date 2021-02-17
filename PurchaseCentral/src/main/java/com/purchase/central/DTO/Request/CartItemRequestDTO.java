package com.purchase.central.DTO.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemRequestDTO {
    private Integer id;
    private Integer quantity;
    private Integer discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getId())
                .append("\n")
                .append(this.getDiscount())
                .append("\n")
                .append(this.getQuantity())
                .append("\n")
                .toString();
    }
}
