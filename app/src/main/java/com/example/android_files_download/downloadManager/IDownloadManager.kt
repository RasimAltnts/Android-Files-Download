package com.example.android_files_download.downloadManager

interface IDownloadManager {
    fun start(packetSize: Int)
    fun finish()
    fun progress(downloadSize: Int, packetSize: Int)
}