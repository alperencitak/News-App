package com.alperencitak.newsapp.domain.usecases.news

import com.alperencitak.newsapp.data.local.NewsDao
import com.alperencitak.newsapp.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.delete(article)
    }

}