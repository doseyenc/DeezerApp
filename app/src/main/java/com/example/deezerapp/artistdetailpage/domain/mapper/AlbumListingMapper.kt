package com.example.deezerapp.artistdetailpage.domain.mapper

import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponse
import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponseData
import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingData
import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingDataData
import javax.inject.Inject

class AlbumListingMapper @Inject constructor() {
    fun mapResultsFromResponse(
        response: AlbumListingResponse?
    ): AlbumListingData {
        return AlbumListingData(
            data = mapFromResponseToData(response?.data),
            total = response?.total,
            next = response?.next
        )
    }

    private fun mapFromResponseToData(albumList: List<AlbumListingResponseData?>?): List<AlbumListingDataData> {
        return albumList?.mapNotNull {
            mapAlbumList(it)
        }?.filter {
            it.id != null
        }.orEmpty()
    }

    private fun mapAlbumList(category: AlbumListingResponseData?): AlbumListingDataData {
        return AlbumListingDataData(
            id = category?.id,
            title = category?.title,
            cover = category?.cover,
            coverSmall = category?.coverSmall,
            coverMedium = category?.coverMedium,
            coverBig = category?.coverBig,
            coverXl = category?.coverXl,
            genreId = category?.genreId,
            releaseDate = category?.releaseDate,
            trackList = category?.trackList,
            type = category?.type
        )
    }
}