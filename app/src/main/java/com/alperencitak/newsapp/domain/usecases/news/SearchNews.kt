package com.alperencitak.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.alperencitak.newsapp.domain.model.Article
import com.alperencitak.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>, searchQuery: String): Flow<PagingData<Article>>{
        return newsRepository.searchNews(sources = sources, searchQuery = searchQuery)
    }

}