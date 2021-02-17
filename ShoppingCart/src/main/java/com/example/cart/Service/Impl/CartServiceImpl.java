package com.example.cart.Service.Impl;

import com.example.cart.DAO.CartDAO;
import com.example.cart.DTO.Request.ArticleRequestDTO;
import com.example.cart.DTO.Request.FoundItemDTO;
import com.example.cart.DTO.Response.ArticleResponseDTO;
import com.example.cart.DTO.Response.CartResponseDTO;
import com.example.cart.DTO.Response.ResponseDTO;
import com.example.cart.Exceptions.ItemNotFoundInCartException;
import com.example.cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl implements CartService {

    private CartDAO repository;
    private Environment env;

    @Autowired
    public CartServiceImpl(CartDAO repository, Environment env) {
        this.repository = repository;
        this.env = env;
    }

    @Override
    public ResponseEntity addToCart(ArticleRequestDTO article) {

            String url = env.getProperty("search.service") + "?id=" + article.getId();
        ArticleResponseDTO articleResponse = new ArticleResponseDTO();
        ResponseDTO response = new ResponseDTO();
        Boolean error = false;
        HttpStatus status = HttpStatus.OK;

        try{
            FoundItemDTO[] itemReq = new RestTemplate().getForObject(url, FoundItemDTO[].class);
            FoundItemDTO item = itemReq[0];
            Double percent = (1.0 - (Double.valueOf(article.getDiscount()) / 100.0));
            Double totalPrice = article.getQuantity() * item.getPrice() * percent;
            articleResponse.setId(item.getId());
            articleResponse.setName(item.getName());
            articleResponse.setQuantity(article.getQuantity());
            articleResponse.setDiscount(article.getDiscount());
            articleResponse.setUnitPrice(item.getPrice());
            articleResponse.setTotalPrice(totalPrice);
            response.setCart(this.repository.addItemToCart(articleResponse));
            response.getStatus().setCode(status.value());
            response.getStatus().setMessage("Completed successfully");
        } catch(ArrayIndexOutOfBoundsException e){
            error = true;
            status = HttpStatus.NOT_FOUND;
            response.setCart(this.repository.getCart());
            response.getStatus().setCode(status.value());
            response.getStatus().setMessage("The id: " + article.getId() + " does not exist");
        } catch (ResourceAccessException e){
            error = true;
            status = HttpStatus.SERVICE_UNAVAILABLE;
            response.setCart(this.repository.getCart());
            response.getStatus().setCode(status.value());
            response.getStatus().setMessage("Failed to connect to server");
        }

        return new ResponseEntity(response, status);
    }

    @Override
    public ResponseEntity getCart() {
        return new ResponseEntity(this.repository.getCart(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Integer id) {
        HttpStatus status = null;
        ResponseDTO response = new ResponseDTO();
        try{
            response.setCart(this.repository.delete(id));
            status = HttpStatus.OK;
            response.getStatus().setCode(status.value());
            response.getStatus().setMessage("Item deleted successfully");
        } catch (ItemNotFoundInCartException e){
            response.setCart(this.repository.getCart());
            status = HttpStatus.NOT_FOUND;
            response.getStatus().setCode(status.value());
            response.getStatus().setMessage(e.getMessage());
        }
        return new ResponseEntity(response, status);
    }

}
