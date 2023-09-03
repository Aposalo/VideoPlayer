package com.aposalo.videoplayer.domain.repository

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.media3.common.MediaItem
import com.aposalo.videoplayer.domain.model.MetaDataReader
import com.aposalo.videoplayer.domain.model.VideoItem
import com.aposalo.videoplayer.utils.Constants.Companion.NO_NAME
import com.aposalo.videoplayer.utils.Constants.Companion.VIDEO_URIS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class VideoPlayerRepository(
    private val savedStateHandle: SavedStateHandle,
    private val metaDataReader: MetaDataReader,
    )
{
    private var videoUris : StateFlow<List<Uri>> = savedStateHandle.getStateFlow(VIDEO_URIS, emptyList())

    fun getMappedVideoItems() : Flow<List<VideoItem>> {
        return videoUris.map { uris ->
            uris.map { uri ->
                VideoItem (
                    contentUri = uri,
                    mediaItem = MediaItem.fromUri(uri),
                    name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: NO_NAME
                )
            }
        }
    }

    fun addVideoUriToLocalDatabase(uri : Uri) {
        val videoUrisNewValue = videoUris.value + uri
        savedStateHandle[VIDEO_URIS] = videoUrisNewValue
    }
}