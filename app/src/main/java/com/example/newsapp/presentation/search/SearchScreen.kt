package com.example.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.Domain.model.Article
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.Dimens.MediumPadding1

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent)->Unit,
    navigateToDetails:(Article)->Unit
){

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
                ).statusBarsPadding().fillMaxSize()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        state.article?.let {
            val article=it.collectAsLazyPagingItems()
            ArticleList(article=article, onClick = {navigateToDetails(it)})
        }
    }

}