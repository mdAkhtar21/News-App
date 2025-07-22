package com.example.newsapp.Domain.usecases.news

import androidx.paging.PagingData
import com.example.newsapp.Domain.model.Article
import com.example.newsapp.Domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository:NewsRepository
) {
    operator fun invoke(searchQuery:String,source: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery=searchQuery,source=source)
    }
}