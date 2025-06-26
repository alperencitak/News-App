package com.alperencitak.newsapp.data.remote.dto

import com.alperencitak.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)