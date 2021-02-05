package com.example.cart.Service;

import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.CartResponseDTO;

public interface CartService {
    CartResponseDTO addToCart(ArticleRequestDTO article);
}
