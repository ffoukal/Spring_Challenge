package com.example.cart.Controller;

import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/")
    public CartResponseDTO addToChart(@RequestParam ArticleRequestDTO article){
        return null;
    }


}
