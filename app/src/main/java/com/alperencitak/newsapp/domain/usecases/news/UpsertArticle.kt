package com.alperencitak.newsapp.domain.usecases.news

import com.alperencitak.newsapp.data.local.NewsDao
import com.alperencitak.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }

}