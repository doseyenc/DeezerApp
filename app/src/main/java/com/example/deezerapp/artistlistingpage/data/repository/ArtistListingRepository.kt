package com.example.deezerapp.artistlistingpage.data.repository

import com.example.deezerapp.artistlistingpage.data.model.ArtistListingResponse
import com.example.deezerapp.artistlistingpage.data.source.ArtistListingDataSource
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.remote
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArtistListingRepository @Inject constructor(
    private val artistListingDataSource: ArtistListingDataSource.Remote
) {
    fun getArtistList(
        id: String,
    ): Observable<Resource<ArtistListingResponse>> {
        return artistListingDataSource
            .getArtistList(
                id = id,
            ).remote()
    }
}