package com.purchase.central.DTO.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequestDTO {
    private List<CartItemRequestDTO> articles;

    public List<CartItemRequestDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<CartItemRequestDTO> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItemRequestDTO i : articles)
            sb.append(i.toString()).append("_________\n");
        return sb.toString();
    }
}
