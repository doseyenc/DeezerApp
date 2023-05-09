package com.example.deezerapp.artistlistingpage.data.model

import com.google.gson.annotations.SerializedName

data class ArtistListingResponse(
    @SerializedName("data")
    val data: List<ArtistListingResponseData>
)
