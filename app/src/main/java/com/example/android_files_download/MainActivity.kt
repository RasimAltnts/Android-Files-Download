package com.example.android_files_download

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.android_files_download.ui.theme.AndroidFilesDownloadTheme
import com.example.android_files_download.ui.theme.ui.DownloadPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidFilesDownloadTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.5f),
                    color = MaterialTheme.colors.background
                ) {
                    DownloadPage()
                }
            }
        }
    }
}