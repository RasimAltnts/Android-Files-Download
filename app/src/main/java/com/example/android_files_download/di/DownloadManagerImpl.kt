package com.example.android_files_download.di

import com.example.android_files_download.downloadManager.DownloadManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object DownloadManagerImpl {

    @Provides
    @Singleton
    fun downloadManager(): DownloadManager {
        return DownloadManager()
    }
}