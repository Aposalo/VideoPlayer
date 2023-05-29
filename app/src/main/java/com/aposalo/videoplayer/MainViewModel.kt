package com.aposalo.videoplayer

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.aposalo.videoplayer.local.AppDatabase
import com.aposalo.videoplayer.repository.VideoPlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val player: Player,
    metaDataReader: MetaDataReader
): ViewModel() {

    private val repository =  VideoPlayerRepository(savedStateHandle, metaDataReader)

    val videoItems = repository.getMappedVideoItems()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList())

    init {
        player.prepare()
    }


    fun addVideoUri(uri : Uri) {
        repository.addVideoUriToLocalDatabase(uri)
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri : Uri) {
        player.setMediaItem(
            videoItems.value.find{it.contentUri == uri}?.mediaItem ?: return
        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}