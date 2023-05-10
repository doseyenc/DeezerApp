package com.example.deezerapp.artistdetailpage.data.repository

import com.example.deezerapp.artistdetailpage.data.model.ArtistDetailResponse
import com.example.deezerapp.artistdetailpage.data.source.ArtistDetailDataSource
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.remote
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArtistDetailRepository @Inject constructor(
    private val artistDetailDataSource: ArtistDetailDataSource.Remote
) {
    fun getArtistDetail(
        id: String,
    ): Observable<Resource<ArtistDetailResponse>> {
        return artistDetailDataSource
            .getArtistDetail(
                id = id,
            ).remote()
    }
}