package com.mihaidinu.newsapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class NewsArticleDto {
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String sourceName;
    private OffsetDateTime publishedAt;

}