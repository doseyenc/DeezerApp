package com.example.deezerapp.artistdetailpage.data.source.service

import com.example.deezerapp.artistdetailpage.data.model.ArtistDetailResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistDetailService {
    @GET("artist/{id}")
    fun getArtistDetail(
        @Path("id") id: String
    ): Single<ArtistDetailResponse>
}