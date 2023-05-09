package com.example.deezerapp.artistlistingpage.data.source.service

import com.example.deezerapp.artistlistingpage.data.model.ArtistListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistListingService {
    @GET("genre/{id}/artists")
    fun getArtistList(
        @Path("id") id: Int
    ): Single<ArtistListingResponse>
}