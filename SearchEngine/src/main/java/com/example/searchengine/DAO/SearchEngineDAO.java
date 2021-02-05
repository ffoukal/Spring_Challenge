package com.example.searchengine.DAO;

import com.example.searchengine.DTO.Validation.SearchValidation;
import com.example.searchengine.Model.Product;

import java.util.List;

public interface SearchEngineDAO {
    List<Product> getAllProducts();
    List<Product> getProductsByFilter(SearchValidation filters);
}
