package com.mihaidinu.newsapp.controller;

import com.mihaidinu.newsapp.model.NewsArticle;
import com.mihaidinu.newsapp.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/latest")
    public List<NewsArticle> getLatestNews() {
        return newsService.fetchLatestNews();
    }
}
