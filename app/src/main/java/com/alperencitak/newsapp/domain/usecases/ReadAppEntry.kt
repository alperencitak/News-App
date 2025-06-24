package com.alperencitak.newsapp.domain.usecases

import com.alperencitak.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}