package com.aposalo.videoplayer.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.aposalo.videoplayer.domain.model.MainViewModel

@Composable
fun GetIconButton(viewModel: MainViewModel){
    val selectVideoLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(viewModel::addVideoUri)
        }
    )

    IconButton(onClick = {
        selectVideoLauncher.launch("video/mp4")
    }) {
        Icon(
            imageVector = Icons.Default.FileOpen,
            contentDescription = "Select video"
        )
    }
}