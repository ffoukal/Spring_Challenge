package com.example.cart.DTO.Response;

import com.example.cart.DTO.Request.ArticleRequestDTO;

import java.util.Date;
import java.util.List;

public class CartResponseDTO {
    private Integer id;
    private Date date;
    private List<ArticleRequestDTO> articles;
    private Double checkedOut;

    public CartResponseDTO() {
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ArticleRequestDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleRequestDTO> articles) {
        this.articles = articles;
    }

    public Double getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(Double checkedOut) {
        this.checkedOut = checkedOut;
    }
}
