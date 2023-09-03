package com.aposalo.videoplayer.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.aposalo.videoplayer.domain.model.MainViewModel
import com.aposalo.videoplayer.utils.Constants.Companion.SELECT_VIDEO
import com.aposalo.videoplayer.utils.Constants.Companion.VIDEO_MP4

@Composable
fun GetIconButton(viewModel: MainViewModel){
    val selectVideoLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(viewModel::addVideoUri)
        }
    )

    IconButton(onClick = {
        selectVideoLauncher.launch(VIDEO_MP4)
    }) {
        Icon(
            imageVector = Icons.Default.FileOpen,
            contentDescription = SELECT_VIDEO
        )
    }
}