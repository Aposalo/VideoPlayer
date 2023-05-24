package com.aposalo.videoplayer.local

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "video_item")
class VideoItemEntry(
    private var uri: String
) : Parcelable