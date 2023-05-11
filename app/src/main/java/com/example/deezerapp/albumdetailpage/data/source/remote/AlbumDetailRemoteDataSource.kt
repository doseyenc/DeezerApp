package com.example.deezerapp.albumdetailpage.data.source.remote

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import com.example.deezerapp.albumdetailpage.data.source.AlbumDetailDataSource
import com.example.deezerapp.albumdetailpage.data.source.service.AlbumDetailService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumDetailRemoteDataSource @Inject constructor(
    private val albumDetailService: AlbumDetailService
) : AlbumDetailDataSource.Remote {
    override fun getAlbumDetail(
        id: String,
    ): Observable<AlbumDetailResponse> {
        return albumDetailService.getAlbumDetail(
            id = id
        ).toObservable()
    }
}