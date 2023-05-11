package com.example.deezerapp.albumdetailpage.data.model

import com.google.gson.annotations.SerializedName

data class TracksResponseData(
    @SerializedName("data")
    val data: List<TracksData>?,
)
