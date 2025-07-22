package com.example.newsapp.presentation.bookmark

import com.example.newsapp.Domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)

