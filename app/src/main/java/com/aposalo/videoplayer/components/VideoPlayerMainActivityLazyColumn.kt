package com.aposalo.videoplayer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aposalo.videoplayer.domain.model.MainViewModel


@Composable
fun GetLazyColumn(viewModel: MainViewModel) {
    val videoItems by viewModel.videoItems.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(videoItems) { item ->
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.playVideo(item.contentUri)
                    }
                    .padding(16.dp)
            )
        }
    }
}