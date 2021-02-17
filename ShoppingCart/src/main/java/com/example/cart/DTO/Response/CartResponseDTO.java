package com.example.cart.DTO.Response;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartResponseDTO {
    private String date;
    private List<ArticleResponseDTO> articles;
    private Double totalPrice;

    public CartResponseDTO() {
        this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        this.articles = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public List<ArticleResponseDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponseDTO> articles) {
        this.articles = articles;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("date: ");
        sb.append(this.date + "\n");
        sb.append("articles: ");
        sb.append(this.articles + "\n");
        sb.append("totalPrice: ");
        sb.append(this.totalPrice + "\n");

        return sb.toString();
    }
}
