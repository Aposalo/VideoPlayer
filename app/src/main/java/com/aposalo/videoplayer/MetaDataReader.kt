package com.aposalo.videoplayer

import android.app.Application
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

data class MetaData(
    val fileName : String
)

interface MetaDataReader{
    fun getMetaDataFromUri(contentUri: Uri) : MetaData?
}

class MetaDataReaderImpl(
    private val app: Application
) : MetaDataReader{

    override fun getMetaDataFromUri(contentUri: Uri): MetaData? {
        if(contentUri.scheme != "content") {
            return null
        }
        val fileName = app.contentResolver.query(
            contentUri,
            arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
            null,
            null
        )?.use { cursor ->
            val index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(index)
        }
        return fileName?.let{ fullFileName ->
            MetaData(
                fileName = Uri.parse(fullFileName).lastPathSegment ?: return null
            )

        }
    }

}