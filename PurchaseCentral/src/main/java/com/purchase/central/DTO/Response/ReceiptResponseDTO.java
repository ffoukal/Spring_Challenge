package com.purchase.central.DTO.Response;

import java.util.List;

public class ReceiptResponseDTO {
    private Integer id;
    private String status;
    private List<ArticleResponseDTO> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ArticleResponseDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponseDTO> articles) {
        this.articles = articles;
    }
}
