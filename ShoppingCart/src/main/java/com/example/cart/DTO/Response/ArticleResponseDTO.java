package com.example.cart.DTO.Response;

public class ArticleResponseDTO {
    private Integer cartId;
    private Integer id;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private Integer discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(this.id);
        sb.append("\n");
        sb.append("name: ");
        sb.append(this.name);
        sb.append("\n");
        sb.append("Q: ");
        sb.append(this.quantity);
        sb.append("\n");
        sb.append("discount: ");
        sb.append(this.discount);
        sb.append("\n");
        sb.append("price:");
        sb.append(this.unitPrice);
        sb.append("\n");
        sb.append("total: ");
        sb.append(this.totalPrice);
        sb.append("\n");

        return sb.toString();
    }
}
