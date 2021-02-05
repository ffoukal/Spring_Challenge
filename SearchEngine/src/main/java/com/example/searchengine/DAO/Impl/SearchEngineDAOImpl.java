package com.example.searchengine.DAO.Impl;

import com.example.searchengine.DAO.SearchEngineDAO;
import com.example.searchengine.DTO.Validation.SearchValidation;
import com.example.searchengine.Model.Product;
import com.example.searchengine.Utils.FilterBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;

@Repository
public class SearchEngineDAOImpl implements SearchEngineDAO {

    @Override
    public List<Product> getAllProducts() {
        return readJson();
    }

    @Override
    public List<Product> getProductsByFilter(SearchValidation filters) {
        return FilterBuilder.filter(filters, readJson());
    }

    public  List<Product> readJson(){
        try{
            File file = ResourceUtils.getFile("classpath:static/products.json");
            ObjectMapper mapper = new ObjectMapper();
            List<Product> products = mapper.readValue(file, new TypeReference<List<Product>>() {});
            return products;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error reading or processing the JSON file");
        }
    }
}
