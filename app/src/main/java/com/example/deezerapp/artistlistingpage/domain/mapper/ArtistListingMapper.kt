package com.example.deezerapp.artistlistingpage.domain.mapper

import com.example.deezerapp.artistlistingpage.data.model.ArtistListingResponse
import com.example.deezerapp.artistlistingpage.data.model.ArtistListingResponseData
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingData
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingDataData
import javax.inject.Inject

class ArtistListingMapper @Inject constructor() {

    fun mapResultsFromResponse(
        response: ArtistListingResponse?
    ): ArtistListingData {
        return ArtistListingData(
            data = mapFromResponseToData(response?.data)
        )
    }

    private fun mapFromResponseToData(artistList: List<ArtistListingResponseData?>?): List<ArtistListingDataData?> {
        return artistList?.mapNotNull {
            mapArtistList(it)
        }?.filter {
            it.id != null
        }.orEmpty()
    }

    private fun mapArtistList(category: ArtistListingResponseData?): ArtistListingDataData {
        return ArtistListingDataData(
            id = category?.id,
            name = category?.name,
            picture = category?.picture,
            pictureSmall = category?.pictureSmall,
            pictureMedium = category?.pictureMedium,
            pictureBig = category?.pictureBig,
            pictureXl = category?.pictureXl,
            trackList = category?.trackList,
            type = category?.type
        )
    }
}