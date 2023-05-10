package com.example.deezerapp.artistdetailpage.data.source.service

import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumListingService {
    @GET("artist/{id}/albums")
    fun getAlbumList(
        @Path("id") id: String
    ): Single<AlbumListingResponse>
}