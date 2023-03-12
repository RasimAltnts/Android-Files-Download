package com.example.android_files_download.downloadManager

import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadManager {

    /**
     * This is file path by the file by written
     **/
    private val path: File? =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    suspend fun downloadRequest(link: String,title: String,listener: IDownloadManager) {

        //This action is preparing the URL for connection
        val connection: HttpURLConnection = URL(link).openConnection() as HttpURLConnection
        //This action is making the connection for the URL
        connection.connect()

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {

            val fileLength: Int = connection.contentLength
            val packetSize: Int = fileLength / 1000000

            withContext(Dispatchers.Main) {
                listener.start(fileLength)
            }

            val data = ByteArray(4096)
            var total: Long = 0
            var packet: Int
            var count: Int

            val file = File(path, "${title}.zip")

            val outputStream = FileOutputStream(file)
            val inputStream = connection.inputStream


            while (fileLength > total) {

                count = inputStream.read(data,0,4096)
                outputStream?.write(data,0,count)
                total += count
                withContext(Dispatchers.Main) {
                    listener.progress(total.toInt(),fileLength)
                }
                if (fileLength.toLong() == total) {
                    withContext(Dispatchers.Main) {
                        listener.finish()
                    }
                    break
                }
            }

        }
    }

}