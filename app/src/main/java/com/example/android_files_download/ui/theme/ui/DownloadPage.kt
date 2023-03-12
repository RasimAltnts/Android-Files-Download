package com.example.android_files_download.ui.theme.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun DownloadPage() {
    val link = remember { mutableStateOf("") }
    val progress = remember{ mutableStateOf(0) }
    val packetSize = remember { mutableStateOf(0) }
    val downloadPacketSize = remember { mutableStateOf(0) }

    val permissionList = rememberMultiplePermissionsState(
        arrayListOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    )


    Column(modifier = Modifier
        .fillMaxHeight(0.5f)
        .fillMaxWidth(1f)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.33f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            TextField(
                value = link.value  ,
                onValueChange = {
                    link.value = it
                })
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                onClick = {
                    if (permissionList.allPermissionsGranted) {
                        println("link::${link.value}")
                    }
                    else {
                        permissionList.launchMultiplePermissionRequest()
                    }

                }
            ) {
                Text(text = "Start Download", color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            LinearProgressIndicator(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth(0.8f),
                backgroundColor = Color.Black.copy(0.4f),
                progress = 0.5f,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewDownloadPage() {
    DownloadPage()
}