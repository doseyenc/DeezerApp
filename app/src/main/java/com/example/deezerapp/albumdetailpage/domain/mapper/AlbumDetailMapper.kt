package com.example.deezerapp.albumdetailpage.domain.mapper

import com.example.deezerapp.albumdetailpage.data.model.AlbumDetailResponse
import com.example.deezerapp.albumdetailpage.data.model.AlbumResponseData
import com.example.deezerapp.albumdetailpage.data.model.TracksData
import com.example.deezerapp.albumdetailpage.data.model.TracksResponseData
import com.example.deezerapp.albumdetailpage.domain.model.AlbumData
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
import com.example.deezerapp.albumdetailpage.domain.model.TracksDomainData
import javax.inject.Inject

class AlbumDetailMapper @Inject constructor() {
    fun mapResultsFromResponse(
        response: AlbumDetailResponse?
    ): AlbumDetailData {
        return AlbumDetailData(
            id = response?.id,
            title = response?.title,
            link = response?.link,
            share = response?.share,
            cover = response?.cover,
            coverSmall = response?.coverSmall,
            coverMedium = response?.coverMedium,
            coverBig = response?.coverBig,
            coverXl = response?.coverXl,
            tracks = mapTracksFromResponse(response?.tracks),
        )
    }

    private fun mapTracksFromResponse(tracks: TracksResponseData?)
            : TracksDomainData? {
        return TracksDomainData(
            data = mapTracksDataFromResponseToData(tracks?.data),

            )
    }

    private fun mapTracksDataFromResponseToData(data: List<TracksData>?)
            : List<TrackDomainDataData>? {
        return data?.mapNotNull {
            mapTracksData(it)
        }?.filter {
            it.id != null
        }.orEmpty()
    }

    private fun mapTracksData(tracksData: TracksData): TrackDomainDataData {
        return TrackDomainDataData(
            id = tracksData.id,
            title = tracksData.title,
            titleShort = tracksData.titleShort,
            link = tracksData.link,
            duration = tracksData.duration,
            preview = tracksData.preview,
            album = mapAlbumDataFromResponse(tracksData.album),
        )
    }

    private fun mapAlbumDataFromResponse(album: AlbumResponseData?): AlbumData? {
        return AlbumData(
            id = album?.id,
            title = album?.title,
            cover = album?.cover,
            coverSmall = album?.coverSmall,
            coverMedium = album?.coverMedium,
            coverBig = album?.coverBig,
            coverXl = album?.coverXl,
            trackList = album?.trackList,
            type = album?.type,
        )
    }


}