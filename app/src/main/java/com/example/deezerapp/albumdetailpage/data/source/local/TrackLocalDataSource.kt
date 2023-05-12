package com.example.deezerapp.albumdetailpage.data.source.local

import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import com.example.deezerapp.albumdetailpage.data.source.AlbumDetailDataSource
import com.example.deezerapp.albumdetailpage.data.source.local.dao.MusicLocalDao
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TrackLocalDataSource @Inject constructor(
    private val musicLocalDao: MusicLocalDao
): AlbumDetailDataSource.Local {
    override fun getAllTracks(): Observable<List<TrackLocalData>> {
        return musicLocalDao
            .getAllMusics()
            .toObservable()
    }

    override fun saveTrack(trackLocalData: TrackLocalData) {
        return musicLocalDao.saveMusic(
            trackLocalData = trackLocalData
        )
    }

    override fun getTrackById(id: String): Observable<TrackLocalData> {
        return musicLocalDao.getMusicById(
            id = id
        ).toObservable()
    }

    override fun deleteTrack(id: String) {
        return musicLocalDao.deleteMusicById(
            id = id
        )
    }


}