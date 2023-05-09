package com.example.deezerapp.artistdetailpage.data.source.remote

import com.example.deezerapp.artistdetailpage.data.model.ArtistDetailResponse
import com.example.deezerapp.artistdetailpage.data.source.ArtistDetailDataSource
import com.example.deezerapp.artistdetailpage.data.source.service.ArtistDetailService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArtistDetailRemoteDataSource @Inject constructor(
    private val artistDetailService: ArtistDetailService
) : ArtistDetailDataSource.Remote {
    override fun getArtistDetail(
        id: String,
    ): Observable<ArtistDetailResponse> {
        return artistDetailService.getArtistDetail(
            id = id
        ).toObservable()
    }
}