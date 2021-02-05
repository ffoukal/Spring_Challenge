package com.example.searchengine.Service;

import com.example.searchengine.DTO.Response.ProductFoundResponseDTO;
import com.example.searchengine.DTO.Validation.SearchValidation;

import java.util.List;

public interface SearchEngineService {
    List<ProductFoundResponseDTO> getArticlesByFilters(SearchValidation filters);
}
