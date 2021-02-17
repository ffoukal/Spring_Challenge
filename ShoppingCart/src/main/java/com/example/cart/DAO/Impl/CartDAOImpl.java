package com.example.cart.DAO.Impl;

import com.example.cart.DAO.CartDAO;
import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Request.FoundItemDTO;
import com.example.cart.DTO.Response.ArticleResponseDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import com.example.cart.Exceptions.ItemNotFoundInCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@PropertySource("classpath:application.properties")
public class CartDAOImpl implements CartDAO {

    private CartResponseDTO cart;
    private Environment env;

    @Autowired
    public CartDAOImpl(Environment env){
        this.cart = createCart();
        this.env = env;
    }

    @Override
    public CartResponseDTO addItemToCart(ArticleResponseDTO article){
        try{
            List<ArticleResponseDTO> articles = this.cart.getArticles();
            Integer id = this.getLastItemId() + 1;
            article.setCartId(id);
            articles.add(article);
            this.cart.setArticles(articles);
            this.getTotal();
            return this.cart;
        } catch(Exception e){
            e.getMessage();
            throw new RuntimeException("Error saving the cart" + e.getMessage());
        }
    }

    @Override
    public CartResponseDTO delete(Integer id) {
        Boolean found = this.cart.getArticles().stream().anyMatch(i -> i.getCartId() == id);
        if(found){
            List<ArticleResponseDTO> list = this.cart.getArticles()
                    .stream()
                    .filter(i -> i.getCartId() != id)
                    .collect(Collectors.toList());
            this.cart.setArticles(list);
            this.getTotal();
        } else {
            throw new ItemNotFoundInCartException("The item does not exist in cart");
        }

        return this.cart;
    }

    public CartResponseDTO getCart(){
        this.getTotal();
        return this.cart;
    }

    private void getTotal(){
        Double total = this.cart.getArticles().stream()
                .map(i -> i.getTotalPrice())
                .reduce(0.0, Double::sum);
        this.cart.setTotalPrice(total);
    }

    public CartResponseDTO createCart(){
        if(this.cart == null){
            CartResponseDTO cart =  new CartResponseDTO();
            return cart;
        }
        return this.cart;
    }

    public Integer getLastItemId(){
        return this.cart.getArticles().stream().mapToInt(item -> item.getCartId()).max().orElseGet(() -> 0);
    }

}
