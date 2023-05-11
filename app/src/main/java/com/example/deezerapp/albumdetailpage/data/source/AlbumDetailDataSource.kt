package com.example.deezerapp.albumdetailpage.data.source

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import io.reactivex.rxjava3.core.Observable

interface AlbumDetailDataSource {
    interface Remote {
        fun getAlbumDetail(
            id: String
        ): Observable<AlbumDetailResponse>
    }
}