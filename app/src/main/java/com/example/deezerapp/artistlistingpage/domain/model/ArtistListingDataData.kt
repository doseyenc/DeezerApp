package com.example.deezerapp.artistlistingpage.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistListingDataData(
    val id: Int?,
    val name: String?,
    val picture: String?,
    val pictureSmall: String?,
    val pictureMedium: String?,
    val pictureBig: String?,
    val pictureXl: String?,
    val trackList: String?,
    val type: String?
):Parcelable
