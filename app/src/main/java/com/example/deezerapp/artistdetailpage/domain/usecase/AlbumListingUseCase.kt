package com.example.deezerapp.artistdetailpage.domain.usecase

import com.example.deezerapp.artistdetailpage.data.repository.AlbumListingRepository
import com.example.deezerapp.artistdetailpage.domain.mapper.AlbumListingMapper
import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingData
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.mapOnData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumListingUseCase @Inject constructor(
    private val albumListingRepository: AlbumListingRepository,
    private val albumListingMapper: AlbumListingMapper
) {
    fun getAlbumList(
        id: String
    ): Observable<Resource<AlbumListingData>> {
        return albumListingRepository
            .getAlbumList(
                id = id
            ).mapOnData {
                albumListingMapper.mapResultsFromResponse(it)
            }
    }
}