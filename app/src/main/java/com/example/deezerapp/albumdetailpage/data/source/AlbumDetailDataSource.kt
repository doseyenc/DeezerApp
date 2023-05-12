package com.example.deezerapp.albumdetailpage.data.source

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import io.reactivex.rxjava3.core.Observable

interface AlbumDetailDataSource {
    interface Remote {
        fun getAlbumDetail(
            id: String
        ): Observable<AlbumDetailResponse>
    }
    interface Local {
        fun getAllTracks(
        ): Observable<List<TrackLocalData>>

        fun saveTrack(
            trackLocalData: TrackLocalData
        )

        fun getTrackById(
            id: String
        ): Observable<TrackLocalData>

        fun deleteTrack(
            id: String
        )

    }
}