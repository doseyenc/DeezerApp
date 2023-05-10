package com.example.deezerapp.artistdetailpage.domain.model

data class AlbumListingData(
    val data: List<AlbumListingDataData>,
    val total: Int?,
    val next: String?
)
