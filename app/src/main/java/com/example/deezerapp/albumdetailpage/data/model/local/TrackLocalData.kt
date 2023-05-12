package com.example.deezerapp.albumdetailpage.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.deezerapp.albumdetailpage.domain.model.AlbumData

@Entity(
    tableName = "musics"
)
data class TrackLocalData(
    @PrimaryKey
    @ColumnInfo(name = "music_id")
    val musicId : Int?,
    @ColumnInfo(name = "track_title")
    val trackTitle : String?,
    @ColumnInfo(name = "title_short")
    val titleShort : String?,
    @ColumnInfo(name = "link")
    val link : String?,
    @ColumnInfo(name = "duration")
    val duration : Int?,
    @ColumnInfo(name = "preview")
    val preview : String?,
    @Embedded
    val album : AlbumLocalData?
)