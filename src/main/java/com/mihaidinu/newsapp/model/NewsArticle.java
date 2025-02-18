package com.mihaidinu.newsapp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;

import com.mihaidinu.newsapp.utils.OffsetDateTimeDeserializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 1000)
    private String description;
    private String url;
    @Column(length = 500)
    private String image;
    private String source;

    @Column(columnDefinition = "TIMESTAMP")
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime publishedAt;
}
