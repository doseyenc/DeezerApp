package com.example.deezerapp.artistdetailpage.data.source

import com.example.deezerapp.artistdetailpage.data.model.ArtistDetailResponse
import io.reactivex.rxjava3.core.Observable

interface ArtistDetailDataSource {
    interface Remote {
        fun getArtistDetail(
            id:String
        ): Observable<ArtistDetailResponse>
    }
}