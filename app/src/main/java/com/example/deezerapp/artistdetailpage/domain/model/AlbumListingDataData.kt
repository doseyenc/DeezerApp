package com.example.deezerapp.artistdetailpage.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumListingDataData(
    val id: Long?,
    val title: String?,
    val cover: String?,
    val coverSmall: String?,
    val coverMedium: String?,
    val coverBig: String?,
    val coverXl: String?,
    val genreId: Int?,
    val releaseDate: String?,
    val trackList: String?,
    val type: String?
):Parcelable
