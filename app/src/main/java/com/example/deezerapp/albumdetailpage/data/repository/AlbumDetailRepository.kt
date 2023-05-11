package com.example.deezerapp.albumdetailpage.data.repository

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import com.example.deezerapp.albumdetailpage.data.source.AlbumDetailDataSource
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.remote
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumDetailRepository @Inject constructor(
    private val albumDetailDataSource: AlbumDetailDataSource.Remote
) {
    fun getAlbumDetail(
        id: String,
    ): Observable<Resource<AlbumDetailResponse>> {
        return albumDetailDataSource
            .getAlbumDetail(
                id = id,
            ).remote()
    }
}