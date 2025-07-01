package com.alperencitak.newsapp.domain.usecases.news

import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }

}