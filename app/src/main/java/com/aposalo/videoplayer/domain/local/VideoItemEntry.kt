package com.aposalo.videoplayer.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_item")
data class VideoItemEntry(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var uri: String
)