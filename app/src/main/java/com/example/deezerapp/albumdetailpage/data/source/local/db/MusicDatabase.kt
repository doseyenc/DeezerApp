package com.example.deezerapp.albumdetailpage.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deezerapp.albumdetailpage.data.model.local.AlbumLocalData
import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import com.example.deezerapp.albumdetailpage.data.source.local.dao.MusicLocalDao


@Database(entities = [
    TrackLocalData::class,
    AlbumLocalData::class,
],
    version = 1
)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun musicLocalDao(): MusicLocalDao
}