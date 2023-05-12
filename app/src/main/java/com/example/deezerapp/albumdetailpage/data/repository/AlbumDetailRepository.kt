package com.example.deezerapp.albumdetailpage.data.repository

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import com.example.deezerapp.albumdetailpage.data.source.AlbumDetailDataSource
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.remote
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(
    private val albumDetailDataSource: AlbumDetailDataSource.Remote,
    private val albumDetailLocalDataSource: AlbumDetailDataSource.Local,
) {
    //Remote
    fun getAlbumDetail(
        id: String,
    ): Observable<Resource<AlbumDetailResponse>> {
        return albumDetailDataSource
            .getAlbumDetail(
                id = id,
            ).remote()
    }

    //Local
    fun saveTrack(trackLocalData: TrackLocalData) {
        CoroutineScope(Dispatchers.IO).launch {
            albumDetailLocalDataSource.saveTrack(
                trackLocalData = trackLocalData
            )
        }
    }

    fun deleteTrack(
        id: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            albumDetailLocalDataSource.deleteTrack(
                id = id
            )
        }
    }

    fun getTrackById(
        id: String
    ): Observable<Resource<TrackLocalData>> {
        return albumDetailLocalDataSource.getTrackById(
            id = id
        ).remote()
    }

    fun getAllTracks(
    ): Observable<Resource<List<TrackLocalData>>> {
        return albumDetailLocalDataSource
            .getAllTracks()
            .remote()
    }
}