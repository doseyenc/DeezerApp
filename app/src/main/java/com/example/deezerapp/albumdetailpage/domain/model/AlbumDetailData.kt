package com.example.deezerapp.albumdetailpage.domain.model

import com.example.deezerapp.albumdetailpage.data.model.TracksResponseData

data class AlbumDetailData(
    val id : Int?,
    val title : String?,
    val link : String?,
    val share : String?,
    val cover : String?,
    val coverSmall : String?,
    val coverMedium : String?,
    val coverBig : String?,
    val coverXl : String?,
    val tracks : TracksDomainData?,
)
