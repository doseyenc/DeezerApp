package com.example.deezerapp.artistdetailpage.data.source.remote

import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponse
import com.example.deezerapp.artistdetailpage.data.source.AlbumListingDataSource
import com.example.deezerapp.artistdetailpage.data.source.service.AlbumListingService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumListingRemoteDataSource @Inject constructor(
    private val albumListingService: AlbumListingService
) : AlbumListingDataSource.Remote {
    override fun getAlbumList(
        id: String,
    ): Observable<AlbumListingResponse> {
        return albumListingService.getAlbumList(
            id = id
        ).toObservable()
    }
}