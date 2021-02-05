package com.example.cart.Service.Impl;

import com.example.cart.DAO.CartDAO;
import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import com.example.cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartDAO repository;

    @Autowired
    public CartServiceImpl(CartDAO repository) {
        this.repository = repository;
    }

    @Override
    public CartResponseDTO addToCart(ArticleRequestDTO article) {
        return this.repository.addItemToCart(article);
    }
}
