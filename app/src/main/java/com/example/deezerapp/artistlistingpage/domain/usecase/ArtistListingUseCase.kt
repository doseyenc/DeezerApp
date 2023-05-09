package com.example.deezerapp.artistlistingpage.domain.usecase

import com.example.deezerapp.artistlistingpage.data.repository.ArtistListingRepository
import com.example.deezerapp.artistlistingpage.domain.mapper.ArtistListingMapper
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingData
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.mapOnData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArtistListingUseCase @Inject constructor(
    private val artistListingRepository: ArtistListingRepository,
    private val movieDetailMapper: ArtistListingMapper
) {
    fun getArtistList(
        id: String
    ): Observable<Resource<ArtistListingData>> {
        return artistListingRepository
            .getArtistList(
                id = id
            ).mapOnData {
                movieDetailMapper.mapResultsFromResponse(it)
            }
    }
}