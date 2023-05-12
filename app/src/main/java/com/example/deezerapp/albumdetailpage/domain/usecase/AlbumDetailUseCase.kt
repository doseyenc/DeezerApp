package com.example.deezerapp.albumdetailpage.domain.usecase

import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import com.example.deezerapp.albumdetailpage.data.repository.AlbumDetailRepository
import com.example.deezerapp.albumdetailpage.domain.mapper.AlbumDetailMapper
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
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

    fun saveTrack(
        trackLocalData: TrackLocalData
    ) {
        return albumDetailRepository
            .saveTrack(
                trackLocalData = trackLocalData
            )
    }

    fun deleteTrack(
        id: String
    ) {
        return albumDetailRepository
            .deleteTrack(
                id = id
            )
    }

    fun getAllTracks(
    ): Observable<Resource<List<TrackDomainDataData>?>> {
        return albumDetailRepository
            .getAllTracks()
            .mapOnData {
                albumDetailMapper.mapListFromLocal(it)
            }
    }

    fun getTrackById(
        id: String
    ): Observable<Resource<TrackDomainDataData>> {
        return albumDetailRepository
            .getTrackById(
                id = id
            ).mapOnData {
                albumDetailMapper.mapTrackLocalData(it)
            }
    }
}