package com.mihaidinu.newsapp.repository;

import com.mihaidinu.newsapp.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
    Optional<NewsArticle> findByUrl(String url);
}
