package com.alperencitak.newsapp.domain.usecases.news

import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectArticles()
    }

}