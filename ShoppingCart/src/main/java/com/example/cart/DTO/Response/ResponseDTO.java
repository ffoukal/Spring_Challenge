package com.example.cart.DTO.Response;

public class ResponseDTO {
    private CartResponseDTO cart;
    private StatusResponseDTO status;

    public ResponseDTO() {
        this.status = new StatusResponseDTO();
    }

    public CartResponseDTO getCart() {
        return cart;
    }

    public void setCart(CartResponseDTO cart) {
        this.cart = cart;
    }

    public StatusResponseDTO getStatus() {
        return status;
    }

    public void setStatus(StatusResponseDTO status) {
        this.status = status;
    }
}
