package com.example.deezerapp.artistdetailpage.data.model

import com.google.gson.annotations.SerializedName

data class AlbumListingResponse(
    @SerializedName("data")
    val data: List<AlbumListingResponseData>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("next")
    val next: String
)