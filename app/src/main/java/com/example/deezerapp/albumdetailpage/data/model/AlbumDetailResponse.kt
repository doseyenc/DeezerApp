package com.example.deezerapp.albumdetailpage.data.model

import com.google.gson.annotations.SerializedName

data class AlbumDetailResponse(
    @SerializedName("id")
    val id : Int?,
    @SerializedName("title")
    val title : String?,
    @SerializedName("upc")
    val upc : String?,
    @SerializedName("link")
    val link : String?,
    @SerializedName("share")
    val share : String?,
    @SerializedName("cover")
    val cover : String?,
    @SerializedName("cover_small")
    val coverSmall : String?,
    @SerializedName("cover_medium")
    val coverMedium : String?,
    @SerializedName("cover_big")
    val coverBig : String?,
    @SerializedName("cover_xl")
    val coverXl : String?,
    @SerializedName("md5_image")
    val md5Image : String?,
    @SerializedName("genre_id")
    val genreId : Int?,
    @SerializedName("tracks")
    val tracks : TracksResponseData?,
    )
