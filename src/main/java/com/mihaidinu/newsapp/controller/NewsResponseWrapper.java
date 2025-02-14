package com.mihaidinu.newsapp.controller;

import com.mihaidinu.newsapp.model.NewsArticle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsResponseWrapper {
    private String cacheStatus;
    private int totalResults;
    private List<NewsArticle> articles;

    public NewsResponseWrapper(String cacheStatus, int totalResults, List<NewsArticle> articles) {
        this.cacheStatus = cacheStatus;
        this.totalResults = totalResults;
        this.articles = articles;
    }
}