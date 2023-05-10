package com.example.deezerapp.artistdetailpage.data.repository

import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponse
import com.example.deezerapp.artistdetailpage.data.source.AlbumListingDataSource
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.remote
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumListingRepository @Inject constructor(
    private val albumListingDataSource: AlbumListingDataSource.Remote
) {
    fun getAlbumList(
        id: String,
    ): Observable<Resource<AlbumListingResponse>> {
        return albumListingDataSource
            .getAlbumList(
                id = id,
            ).remote()
    }
}