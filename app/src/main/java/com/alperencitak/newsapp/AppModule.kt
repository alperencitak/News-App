package com.alperencitak.newsapp

import android.app.Application
import androidx.room.Room
import com.alperencitak.newsapp.data.local.NewsDao
import com.alperencitak.newsapp.data.local.NewsDatabase
import com.alperencitak.newsapp.data.local.NewsTypeConverter
import com.alperencitak.newsapp.data.manger.LocalUserMangerImpl
import com.alperencitak.newsapp.data.remote.NewsApi
import com.alperencitak.newsapp.data.repository.NewsRepositoryImpl
import com.alperencitak.newsapp.domain.manger.LocalUserManger
import com.alperencitak.newsapp.domain.repository.NewsRepository
import com.alperencitak.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.alperencitak.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.alperencitak.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.alperencitak.newsapp.domain.usecases.news.DeleteArticle
import com.alperencitak.newsapp.domain.usecases.news.GetNews
import com.alperencitak.newsapp.domain.usecases.news.NewsUseCases
import com.alperencitak.newsapp.domain.usecases.news.SearchNews
import com.alperencitak.newsapp.domain.usecases.news.SelectArticles
import com.alperencitak.newsapp.domain.usecases.news.UpsertArticle
import com.alperencitak.newsapp.util.Constants.BASE_URL
import com.alperencitak.newsapp.util.Constants.NEWS_DATABASE_NAME
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
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}