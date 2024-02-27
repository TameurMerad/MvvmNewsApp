package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manger.LocalUserMangerImpl
import com.loc.newsapp.domain.manger.LocalUserManger
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.app_entry.ReadEntry
import com.loc.newsapp.domain.usecases.app_entry.SaveEntry
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
        application:Application
    ):LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readEntry = ReadEntry(localUserManger),
        saveEntry = SaveEntry(localUserManger)
    )

}