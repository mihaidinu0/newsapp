package com.mihaidinu.newsapp.repository;

import com.mihaidinu.newsapp.model.NewsCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface NewsCacheRepository extends JpaRepository<NewsCache, Long> {
    NewsCache findByCacheKeyAndFetchedAtAfter(String cacheKey, LocalDateTime time);
}