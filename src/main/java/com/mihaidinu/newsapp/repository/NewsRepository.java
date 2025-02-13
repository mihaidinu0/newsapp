package com.mihaidinu.newsapp.repository;

import com.mihaidinu.newsapp.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
}
