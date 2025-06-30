package com.alperencitak.newsapp.presentation.bookmark

import com.alperencitak.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)