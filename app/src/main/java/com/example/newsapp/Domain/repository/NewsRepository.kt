package com.example.newsapp.Domain.repository

import androidx.paging.PagingData
import com.example.newsapp.Domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(source:List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String,source:List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles():Flow<List<Article>>

    suspend fun selectArticle(url:String):Article?
}