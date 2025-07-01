package com.alperencitak.newsapp.domain.usecases.news

import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }

}