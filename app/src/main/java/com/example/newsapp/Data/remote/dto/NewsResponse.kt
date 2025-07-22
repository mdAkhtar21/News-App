package com.example.newsapp.Data.remote.dto

import com.example.newsapp.Domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)