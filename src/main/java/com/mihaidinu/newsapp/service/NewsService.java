package com.mihaidinu.newsapp.service;

import com.mihaidinu.newsapp.dto.NewsArticleDto;
import com.mihaidinu.newsapp.dto.NewsResponse;
import com.mihaidinu.newsapp.model.NewsArticle;
import com.mihaidinu.newsapp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final RestTemplate restTemplate;

    @Value("${news.api.url}")
    private String newsApiUrl;

    @Value("${news.api.key}")
    private String apiKey;

    public NewsService(NewsRepository newsRepository, RestTemplate restTemplate) {
        this.newsRepository = newsRepository;
        this.restTemplate = restTemplate;
    }

    public List<NewsArticle> fetchLatestNews() {
        String url = String.format("%s?apiKey=%s&country=us", newsApiUrl, apiKey);

        NewsResponse response = restTemplate.getForObject(url, NewsResponse.class);

        if (response != null && response.getArticles() != null) {
            List<NewsArticle> articles = response.getArticles().stream().map(this::convertToEntity).collect(Collectors.toList());

            newsRepository.saveAll(articles);
            return articles;
        }

        return List.of();
    }

    private NewsArticle convertToEntity(NewsArticleDto dto) {
        NewsArticle article = new NewsArticle();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setUrl(dto.getUrl());
        article.setImage(dto.getUrlToImage());
        article.setSource(dto.getSourceName());
        article.setPublishedAt(dto.getPublishedAt());
        return article;
    }
}
