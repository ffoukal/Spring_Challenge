package com.example.cart.DAO;

import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.CartResponseDTO;

import java.util.List;

public interface CartDAO {
    CartResponseDTO addItemToCart(ArticleRequestDTO cart);
}
