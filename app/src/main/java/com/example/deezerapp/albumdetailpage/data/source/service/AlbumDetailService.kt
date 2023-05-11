package com.example.deezerapp.albumdetailpage.data.source.service

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumDetailService {
    @GET("album/{id}")
    fun getAlbumDetail(
        @Path("id") id: String
    ): Single<AlbumDetailResponse>
}