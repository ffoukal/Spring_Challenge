package com.example.searchengine.Controller;

import com.example.searchengine.DTO.Response.ProductFoundResponseDTO;
import com.example.searchengine.DTO.Validation.SearchValidation;
import com.example.searchengine.Service.SearchEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class SearchEngineController {

    private SearchEngineService service;

    @Autowired
    public SearchEngineController(SearchEngineService service) {
        this.service = service;
    }

    @GetMapping("/articles")
    public List<ProductFoundResponseDTO> getFromDTO(SearchValidation search){
        return this.service.getArticlesByFilters(search);
    }

}
