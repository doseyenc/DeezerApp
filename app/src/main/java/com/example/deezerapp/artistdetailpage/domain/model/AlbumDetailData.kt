package com.example.deezerapp.artistdetailpage.domain.model

data class AlbumDetailData(
    val data : List<AlbumDetailDataData>,
    val total : Int,
    val next : String
)
