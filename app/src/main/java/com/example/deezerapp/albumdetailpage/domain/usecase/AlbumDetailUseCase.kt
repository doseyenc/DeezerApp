package com.example.deezerapp.albumdetailpage.domain.usecase

import com.example.deezerapp.albumdetailpage.data.repository.AlbumDetailRepository
import com.example.deezerapp.albumdetailpage.domain.mapper.AlbumDetailMapper
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.mapOnData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AlbumDetailUseCase @Inject constructor(
    private val albumDetailRepository: AlbumDetailRepository,
    private val albumDetailMapper: AlbumDetailMapper
) {
    fun getAlbumDetail(
        id: String
    ): Observable<Resource<AlbumDetailData>> {
        return albumDetailRepository
            .getAlbumDetail(
                id = id
            ).mapOnData {
                albumDetailMapper.mapResultsFromResponse(it)
            }
    }
}