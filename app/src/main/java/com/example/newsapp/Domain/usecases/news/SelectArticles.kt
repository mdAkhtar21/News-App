package com.example.newsapp.Domain.usecases.news

import com.example.newsapp.Data.local.NewsDao
import com.example.newsapp.Domain.model.Article
import com.example.newsapp.Domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
         return newsRepository.selectArticles()
    }
}