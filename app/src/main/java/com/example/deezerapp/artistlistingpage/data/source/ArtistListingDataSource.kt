package com.example.deezerapp.artistlistingpage.data.source

import com.example.deezerapp.artistlistingpage.data.model.ArtistListingResponse
import io.reactivex.rxjava3.core.Observable

interface ArtistListingDataSource {
    interface Remote {
        fun getArtistList(
            id:String
        ): Observable<ArtistListingResponse>
    }
}