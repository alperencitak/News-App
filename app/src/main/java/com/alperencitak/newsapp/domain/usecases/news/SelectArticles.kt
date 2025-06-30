package com.alperencitak.newsapp.domain.usecases.news

import com.alperencitak.newsapp.data.local.NewsDao
import com.alperencitak.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsDao.getArticles()
    }

}