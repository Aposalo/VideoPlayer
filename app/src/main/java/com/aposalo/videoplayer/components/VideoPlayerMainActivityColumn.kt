package com.aposalo.videoplayer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aposalo.videoplayer.domain.model.MainViewModel

class VideoPlayerMainActivityColumn {
    companion object{
        @Composable
        fun GetColumn(viewModel: MainViewModel){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                VideoPlayerMainActivityAndroidView.GetAndroidView(viewModel)
                GetSpacer(8.dp)
                VideoPlayerMainActivityIconButton.GetIconButton(viewModel)
                GetSpacer(16.dp)
                VideoPlayerMainActivityLazyColumn.GetLazyColumn(viewModel)
            }
        }

        @Composable
        fun GetSpacer(height: Dp){
            Spacer(modifier = Modifier.height(height))
        }
    }
}