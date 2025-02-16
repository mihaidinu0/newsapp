package com.mihaidinu.newsapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class NewsResponse {
    private String status;
    private int totalResults;
    private List<NewsArticleDto> articles;

}
