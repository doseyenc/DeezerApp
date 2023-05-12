package com.example.deezerapp.albumdetailpage.data.model

import androidx.room.TypeConverter
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MusicDataConverter {
    @TypeConverter
    fun fromMusicLocalDataDayList(value: List<AlbumDetailData?>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<AlbumDetailData>>() {}.type
        return gson.toJson(value, type)
    }
}