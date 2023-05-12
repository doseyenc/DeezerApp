package com.example.deezerapp.albumdetailpage.data.model

import com.google.gson.annotations.SerializedName

data class TracksData(
    @SerializedName("id")
    val id : Long?,
    @SerializedName("readable")
    val readable : Boolean?,
    @SerializedName("title")
    val title : String?,
    @SerializedName("title_short")
    val titleShort : String?,
    @SerializedName("title_version")
    val titleVersion : String?,
    @SerializedName("link")
    val link : String?,
    @SerializedName("duration")
    val duration : Int?,
    @SerializedName("rank")
    val rank : Int?,
    @SerializedName("explicit_lyrics")
    val explicitLyrics : Boolean?,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics : Int?,
    @SerializedName("explicit_content_cover")
    val explicitContentCover : Int?,
    @SerializedName("preview")
    val preview : String?,
    @SerializedName("album")
    val album : AlbumResponseData?,
)
