package com.aposalo.videoplayer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aposalo.videoplayer.domain.model.MainViewModel
import com.aposalo.videoplayer.ui.theme.MediumGray

@Composable
fun GetBox(viewModel: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MediumGray)
        .padding(16.dp)
    ) {
        GetColumn(viewModel)
    }
}