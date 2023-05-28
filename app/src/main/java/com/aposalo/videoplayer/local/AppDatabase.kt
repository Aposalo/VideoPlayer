package com.aposalo.videoplayer.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(VideoItemEntry::class)], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun videoItemDao(): VideoItemDao

}