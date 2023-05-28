package com.aposalo.videoplayer.repository

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.media3.common.MediaItem
import com.aposalo.videoplayer.MetaDataReader
import com.aposalo.videoplayer.VideoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoPlayerRepository(
    private val savedStateHandle: SavedStateHandle,
    private val metaDataReader: MetaDataReader
    )
{
    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<Uri>())

    fun getMappedVideoItems(): Flow<List<VideoItem>> {
        return videoUris.map { uris ->
            uris.map { uri ->
                VideoItem(
                    contentUri = uri,
                    mediaItem = MediaItem.fromUri(uri),
                    name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: "No Name"
                )
            }
        }
    }

    fun addVideoUriToLocalDatabase(uri : Uri){
        savedStateHandle["videoUris"] = videoUris.value + uri
    }
}