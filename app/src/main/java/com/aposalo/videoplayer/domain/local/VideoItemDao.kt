package com.aposalo.videoplayer.domain.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface VideoItemDao {
    @Insert
    suspend fun insertVideoItem(videoItemEntry: VideoItemEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVideoItem(videoItemEntry: VideoItemEntry)

    @Delete
    suspend fun deleteVideoItem(videoItemEntry: VideoItemEntry)

    @Query("SELECT * FROM video_item")
    suspend fun getVideoItems() : List<VideoItemEntry>

    @Query("SELECT * FROM video_item where uri=:uri")
    suspend fun getVideoItem(uri:String) : List<VideoItemEntry>
}