package com.example.deezerapp.albumdetailpage.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumLocalData(
    @PrimaryKey
    @ColumnInfo(name = "album_id")
    val id: Long?,
    @ColumnInfo(name = "album_title")
    val albumTitle: String?,
    @ColumnInfo(name = "album_cover")
    val cover: String?,
    @ColumnInfo(name = "album_cover_small")
    val coverSmall: String?,
    @ColumnInfo(name = "album_cover_medium")
    val coverMedium: String?,
    @ColumnInfo(name = "album_cover_big")
    val coverBig: String?,
    @ColumnInfo(name = "album_cover_xl")
    val coverXl: String?,
    @ColumnInfo(name = "album_track_list")
    val trackList: String?,
    @ColumnInfo(name = "album_type")
    val type: String?,
)