package com.example.deezerapp.albumdetailpage.domain.model


data class TrackDomainDataData(
    val id : Int?,
    val title : String?,
    val titleShort : String?,
    val link : String?,
    val duration : Int?,
    val preview : String?,
    val album : AlbumData?
)
