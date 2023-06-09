package com.example.deezerapp.categories.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponseData(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("picture_big")
    val pictureBig: String?,

    @SerializedName("picture_medium")
    val pictureMedium: String?,

    @SerializedName("picture_small")
    val pictureSmall: String?,

    @SerializedName("picture_xl")
    val pictureXl: String?,

    @SerializedName("type")
    val type: String?
)
