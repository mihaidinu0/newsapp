package com.mihaidinu.newsapp.service;

import com.mihaidinu.newsapp.dto.NewsArticleDto;
import com.mihaidinu.newsapp.dto.NewsResponse;
import com.mihaidinu.newsapp.model.NewsArticle;
import com.mihaidinu.newsapp.model.NewsCache;
import com.mihaidinu.newsapp.repository.NewsCacheRepository;
import com.mihaidinu.newsapp.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsCacheRepository newsCacheRepository;
    private final RestTemplate restTemplate;

    @Value("${news.api.url}")
    private String newsApiUrl;

    @Value("${news.api.key}")
    private String apiKey;

    public NewsService(NewsRepository newsRepository, NewsCacheRepository newsCacheRepository, RestTemplate restTemplate) {
        this.newsRepository = newsRepository;
        this.newsCacheRepository = newsCacheRepository;
        this.restTemplate = restTemplate;
    }

    public List<NewsArticle> fetchLatestNews(String country, String category, String sources, String query) {
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        String cacheKey = generateCacheKey(country, category, sources, query);

        NewsCache cache = newsCacheRepository.findByCacheKeyAndFetchedAtAfter(cacheKey, oneDayAgo);
        if (cache != null) {
            log.info("✅ Returning cached news for key: {}", cacheKey);
            return cache.getArticles();
        }

        log.info("🌎 Fetching fresh news from NewsAPI for key: {}", cacheKey);

        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(newsApiUrl)
                .queryParam("apiKey", apiKey);

        if (sources != null && !sources.isEmpty()) {
            urlBuilder.queryParam("sources", sources);
        } else {
            if (country != null && !country.isEmpty()) {
                urlBuilder.queryParam("country", country);
            }
            if (category != null && !category.isEmpty()) {
                urlBuilder.queryParam("category", category);
            }
        }

        if (query != null && !query.isEmpty()) {
            urlBuilder.queryParam("q", query);
        }

        String finalUrl = urlBuilder.toUriString();
        log.info("🌎 Fetching fresh news from NewsAPI: {}", finalUrl);

        // Call NewsAPI
        NewsResponse response = restTemplate.getForObject(finalUrl, NewsResponse.class);

        if (response != null && response.getArticles() != null) {
            List<NewsArticle> articles = response.getArticles().stream()
                    .map(this::convertToEntity)
                    .collect(Collectors.toList());

            List<NewsArticle> savedArticles = newsRepository.saveAll(articles);

            NewsCache newCache = new NewsCache();
            newCache.setCacheKey(cacheKey);
            newCache.setFetchedAt(LocalDateTime.now());
            newCache.setArticles(savedArticles);

            newsCacheRepository.save(newCache);

            log.info("💾 Stored {} articles in cache for key: {}", articles.size(), cacheKey);
            return savedArticles;
        }

        log.warn("⚠️ Failed to fetch news from NewsAPI for key: {}", cacheKey);
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

    private String generateCacheKey(String country, String category, String sources, String query) {
        return String.format("country:%s-category:%s-sources:%s-q:%s",
                country != null ? country : "none",
                category != null ? category : "none",
                sources != null ? sources : "none",
                query != null ? query : "none");
    }
}
