package com.alperencitak.newsapp

import android.app.Application
import com.alperencitak.newsapp.data.manger.LocalUserMangerImpl
import com.alperencitak.newsapp.data.remote.NewsApi
import com.alperencitak.newsapp.data.repository.NewsRepositoryImpl
import com.alperencitak.newsapp.domain.manger.LocalUserManger
import com.alperencitak.newsapp.domain.repository.NewsRepository
import com.alperencitak.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.alperencitak.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.alperencitak.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.alperencitak.newsapp.domain.usecases.news.GetNews
import com.alperencitak.newsapp.domain.usecases.news.NewsUseCases
import com.alperencitak.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntries(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }

}