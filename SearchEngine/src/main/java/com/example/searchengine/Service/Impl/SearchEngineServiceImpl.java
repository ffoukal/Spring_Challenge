package com.example.searchengine.Service.Impl;

import com.example.searchengine.DAO.SearchEngineDAO;
import com.example.searchengine.DTO.Response.ProductFoundResponseDTO;
import com.example.searchengine.DTO.Validation.SearchValidation;
import com.example.searchengine.Model.Product;
import com.example.searchengine.Service.SearchEngineService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchEngineServiceImpl implements SearchEngineService {

    private SearchEngineDAO repository;

    @Autowired
    public SearchEngineServiceImpl(SearchEngineDAO repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductFoundResponseDTO> getArticlesByFilters(SearchValidation filters) {
        List<Product> products = this.repository.getProductsByFilter(filters);
        List<ProductFoundResponseDTO> productsResponse = new ArrayList<>();
        ObjectMapper om = new ObjectMapper(); // se mapea a un DTO para no devolver el modelo literal
        productsResponse = om.convertValue(products, new TypeReference<List<ProductFoundResponseDTO>>() {});
        return productsResponse;
    }
}
