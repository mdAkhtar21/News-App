package com.example.newsapp.Domain.usecases.news

import com.example.newsapp.Data.local.NewsDao
import com.example.newsapp.Domain.model.Article
import com.example.newsapp.Domain.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}