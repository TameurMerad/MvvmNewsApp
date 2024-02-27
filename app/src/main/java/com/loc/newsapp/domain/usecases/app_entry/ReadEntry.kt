package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadEntry (
    private val localUserManger: LocalUserManger
) {
     operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}

