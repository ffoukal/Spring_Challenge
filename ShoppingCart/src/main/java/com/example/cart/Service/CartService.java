package com.example.cart.Service;

import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity addToCart(ArticleRequestDTO article);

    ResponseEntity getCart();

//    ResponseEntity checkout();

    ResponseEntity delete(Integer id);
}
