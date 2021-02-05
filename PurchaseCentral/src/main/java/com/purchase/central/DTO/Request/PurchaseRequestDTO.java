package com.purchase.central.DTO.Request;

import java.util.List;

public class PurchaseRequestDTO {
    private String userName;
    private List<ArticleRequestDTO> articles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ArticleRequestDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleRequestDTO> articles) {
        this.articles = articles;
    }
}
