package com.example.cart.Controller;

import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.ArticleResponseDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import com.example.cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity addToChart(@RequestBody ArticleRequestDTO article){
        return this.service.addToCart(article);
    }

    @GetMapping
    public ResponseEntity getCart(){
        return this.service.getCart();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItemFromCart(@PathVariable Integer id){
        return this.service.delete(id);
    }

}

