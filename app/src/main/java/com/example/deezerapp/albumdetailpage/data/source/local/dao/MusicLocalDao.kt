package com.example.deezerapp.albumdetailpage.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import io.reactivex.rxjava3.core.Single

@Dao
interface MusicLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMusic(trackLocalData: TrackLocalData)

    @Query("delete from musics where music_id = :id")
    fun deleteMusicById(id: String)

    @Query("select * from musics where music_id = :id")
    fun getMusicById(id: String): Single<TrackLocalData>

    @Query("select * from musics")
    fun getAllMusics(): Single<List<TrackLocalData>>
}