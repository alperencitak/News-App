package com.alperencitak.newsapp

import android.app.Application
import com.alperencitak.newsapp.data.manger.LocalUserMangerImpl
import com.alperencitak.newsapp.domain.manger.LocalUserManger
import com.alperencitak.newsapp.domain.usecases.AppEntryUseCases
import com.alperencitak.newsapp.domain.usecases.ReadAppEntry
import com.alperencitak.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}