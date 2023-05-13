package com.example.deezerapp.artistlistingpage.data.model

import com.google.gson.annotations.SerializedName

data class ArtistListingResponseData(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("picture_small")
    val pictureSmall: String?,

    @SerializedName("picture_medium")
    val pictureMedium: String?,

    @SerializedName("picture_big")
    val pictureBig: String?,

    @SerializedName("picture_xl")
    val pictureXl: String?,

    @SerializedName("radio")
    val radio: Boolean?,

    @SerializedName("tracklist")
    val trackList: String?,

    @SerializedName("type")
    val type: String?
)
