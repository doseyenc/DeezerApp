package com.example.deezerapp.artistdetailpage.domain.usecase

import com.example.deezerapp.artistdetailpage.data.repository.ArtistDetailRepository
import com.example.deezerapp.artistdetailpage.domain.mapper.ArtistDetailMapper
import com.example.deezerapp.artistdetailpage.domain.model.ArtistDetailData
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.mapOnData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ArtistDetailUseCase @Inject constructor(
    private val artistDetailRepository: ArtistDetailRepository,
    private val artistDetailMapper: ArtistDetailMapper
) {
    fun getArtistDetail(
        id: String
    ): Observable<Resource<ArtistDetailData>> {
        return artistDetailRepository
            .getArtistDetail(
                id = id
            ).mapOnData {
                artistDetailMapper.mapResultsFromResponse(it)
            }
    }
}