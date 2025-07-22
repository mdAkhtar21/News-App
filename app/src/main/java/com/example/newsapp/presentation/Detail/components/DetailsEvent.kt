package com.example.newsapp.presentation.Detail.components

import com.example.newsapp.Domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}