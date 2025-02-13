package com.mihaidinu.newsapp.dto;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<NewsArticleDto> articles;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

    public List<NewsArticleDto> getArticles() { return articles; }
    public void setArticles(List<NewsArticleDto> articles) { this.articles = articles; }
}
