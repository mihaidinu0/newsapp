package com.mihaidinu.newsapp.controller;

import com.mihaidinu.newsapp.model.NewsArticle;
import com.mihaidinu.newsapp.service.NewsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public NewsResponseWrapper getLatestNews(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sources,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        List<NewsArticle> articles = newsService.fetchLatestNews(country, category, sources, q, page, pageSize);
        boolean isCached = !articles.isEmpty();

        return new NewsResponseWrapper(isCached ? "HIT" : "MISS", articles.size(), articles);
    }
}