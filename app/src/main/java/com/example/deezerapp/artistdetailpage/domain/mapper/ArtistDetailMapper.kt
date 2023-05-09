package com.example.deezerapp.artistdetailpage.domain.mapper

import com.example.deezerapp.artistdetailpage.data.model.ArtistDetailResponse
import com.example.deezerapp.artistdetailpage.domain.model.ArtistDetailData

class ArtistDetailMapper {
    fun mapResultsFromResponse(
        response: ArtistDetailResponse?
    ): ArtistDetailData {
        return ArtistDetailData(
            id = response?.id.toString(),
            name = response?.name,
            link = response?.link,
            share = response?.share,
            picture = response?.picture,
            pictureSmall = response?.pictureSmall,
            pictureMedium = response?.pictureMedium,
            pictureXl = response?.pictureXl,
            radio = response?.radio
        )
    }
}