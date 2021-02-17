package com.example.cart.DAO;

import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.ArticleResponseDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CartDAO {
    CartResponseDTO addItemToCart(ArticleResponseDTO cart);
    CartResponseDTO getCart();
    CartResponseDTO delete(Integer id);
}
