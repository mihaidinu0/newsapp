package com.mihaidinu.newsapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class NewsCache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cacheKey;
    private LocalDateTime fetchedAt;

    @ManyToMany
    @JoinTable(
            name = "news_cache_articles",
            joinColumns = @JoinColumn(name = "cache_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<NewsArticle> articles;
}
