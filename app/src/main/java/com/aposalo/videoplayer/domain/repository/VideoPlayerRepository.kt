package com.aposalo.videoplayer.domain.repository

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.media3.common.MediaItem
import com.aposalo.videoplayer.domain.model.MetaDataReader
import com.aposalo.videoplayer.domain.model.VideoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class VideoPlayerRepository(
    private val savedStateHandle: SavedStateHandle,
    private val metaDataReader: MetaDataReader,
    )
{
    var videoUris : StateFlow<List<Uri>> = savedStateHandle.getStateFlow("videoUris", emptyList())

    fun getMappedVideoItems() : Flow<List<VideoItem>> {
        return videoUris.map { uris ->
            uris.map { uri ->
                VideoItem (
                    contentUri = uri,
                    mediaItem = MediaItem.fromUri(uri),
                    name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: "No Name"
                )
            }
        }
    }

    fun addVideoUriToLocalDatabase(uri : Uri) {
        val videoUrisNewValue = videoUris.value + uri//na pernaei th kainourgia timh sth topikh bash
        savedStateHandle["videoUris"] = videoUrisNewValue
    }
}