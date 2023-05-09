package com.example.deezerapp.artistlistingpage.data.source.remote

import com.example.deezerapp.artistlistingpage.data.model.ArtistListingResponse
import com.example.deezerapp.artistlistingpage.data.source.ArtistListingDataSource
import com.example.deezerapp.artistlistingpage.data.source.service.ArtistListingService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArtistListingRemoteDataSource @Inject constructor(
    private val artistListingService: ArtistListingService
) : ArtistListingDataSource.Remote {
    override fun getArtistList(
        id: String,
    ): Observable<ArtistListingResponse> {
        return artistListingService.getArtistList(
            id = id
        ).toObservable()
    }
}