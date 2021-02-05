package com.example.cart.DAO.Impl;

import com.example.cart.DAO.CartDAO;
import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    private CartResponseDTO cart = null;

    public CartDAOImpl(){
        this.cart = createCart();
    }

    @Override
    public CartResponseDTO addItemToCart(ArticleRequestDTO article){
        try{
            this.cart.getArticles().add(article);
            return this.cart;
        } catch(Exception e){
            e.getMessage();
            throw new RuntimeException("Error saving the cart");
        }
    }

    public CartResponseDTO createCart(){
        if(this.cart == null){
            CartResponseDTO cart =  new CartResponseDTO();
            return cart;
        }
        return this.cart;
    }

    public Integer getLastId(){
        return readJson().stream().mapToInt(item -> item.getId()).max().orElseGet(() -> 0);
    }

    public List<CartResponseDTO> readJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new ClassPathResource("static/receipts.json").getFile(), new TypeReference<List<CartResponseDTO>>() {});
        } catch (Exception e){
            throw new RuntimeException("Error reading JSON file");
        }
    }

}
