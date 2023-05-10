package com.example.deezerapp.artistdetailpage.data.source

import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponse
import io.reactivex.rxjava3.core.Observable

interface AlbumListingDataSource {
    interface Remote {
        fun getAlbumList(
            id: String
        ): Observable<AlbumListingResponse>
    }
}