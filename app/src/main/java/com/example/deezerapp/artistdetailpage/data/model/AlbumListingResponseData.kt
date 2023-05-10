package com.example.deezerapp.artistdetailpage.data.model

import com.google.gson.annotations.SerializedName

data class AlbumListingResponseData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_big")
    val coverBig: String,
    @SerializedName("cover_xl")
    val coverXl: String,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("genre_id")
    val genreId: Int,
    @SerializedName("fans")
    val fans: Int,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("record_type")
    val recordType: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("type")
    val type: String
)
