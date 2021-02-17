package com.purchase.central.Service.Impl;

import com.purchase.central.DAO.PurchaseDAO;
import com.purchase.central.DTO.Request.ArticleRequestDTO;
import com.purchase.central.DTO.Request.CartItemRequestDTO;
import com.purchase.central.DTO.Request.CartRequestDTO;
import com.purchase.central.DTO.Request.PurchaseRequestDTO;
import com.purchase.central.DTO.Response.ArticleResponseDTO;
import com.purchase.central.DTO.Response.PurchaseResponseDTO;
import com.purchase.central.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class PurchaseServiceImpl implements PurchaseService {

    private Environment env;
    private PurchaseDAO repository;

    @Autowired
    public PurchaseServiceImpl(Environment env, PurchaseDAO repository) {
        this.env = env;
        this.repository = repository;
    }

    @Override
    public ResponseEntity purchase(PurchaseRequestDTO request) {
        PurchaseResponseDTO response = new PurchaseResponseDTO();
        List<ArticleResponseDTO> articles = new ArrayList<>();
        Boolean error = false;
        HttpStatus status = HttpStatus.OK;

        response.getStatusCode().setCode(200);
        response.getStatusCode().setMessage("La solicitud se completo completamente");
        response.getReceipt().setStatus("PENDING");

        for(ArticleRequestDTO i : request.getArticles()){
            String url = env.getProperty("search.service") + "?id=" + i.getProductId();
                ArticleResponseDTO item = null;
            try{
                item = new RestTemplate().getForObject(url, ArticleResponseDTO[].class)[0];
                item.setPrice((item.getPrice() * i.getQuantity())*(1.0-(Double.valueOf(i.getDiscount())/100.0)));
                if(item.getQuantity() < i.getQuantity()){
                    error = true;
                    status = HttpStatus.BAD_REQUEST;
                    response.getStatusCode().setCode(status.value());
                    response.getReceipt().setStatus("ERROR");
                    response.getStatusCode().setMessage("The available stock of the article '" + item.getName() + "' is less than required");
                }
                articles.add(item);
            } catch(ArrayIndexOutOfBoundsException e){
                error = true;
                status = HttpStatus.NOT_FOUND;
                response.getStatusCode().setCode(status.value());
                response.getReceipt().setStatus("ERROR");
                response.getStatusCode().setMessage("The id: " + i.getProductId() + " does not exist");
            } catch (ResourceAccessException e){
                error = true;
                status = HttpStatus.SERVICE_UNAVAILABLE;
                response.getStatusCode().setCode(status.value());
                response.getReceipt().setStatus("ERROR");
                response.getStatusCode().setMessage("Failed to connect to server");
            }
        }

        if(!error){
            response.getReceipt().setArticles(articles);
            response.getReceipt().setId(repository.createReceipt(response.getReceipt()));
        } else {
            response.setReceipt(null);
        }

        return new ResponseEntity(response, status);
    }



    @Override
    public ResponseEntity checkout() {
        PurchaseRequestDTO request = new PurchaseRequestDTO();
        String url = env.getProperty("cart.service");
        HttpStatus status = null;
        try{
            CartRequestDTO cart =   new RestTemplate().getForObject(url, CartRequestDTO.class);
            List<ArticleRequestDTO> articles = new ArrayList<>();
            for(CartItemRequestDTO item : cart.getArticles()){
                ArticleRequestDTO article = new ArticleRequestDTO();
                article.setProductId(item.getId());
                article.setQuantity(item.getQuantity());
                article.setDiscount(item.getDiscount());
                articles.add(article);
            }
            request.setArticles(articles);
            return this.purchase(request);
        } catch (Exception e){

        }

        return null;
    }
}
