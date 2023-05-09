package com.example.deezerapp.categories.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesDataData(
    val id: Int?,
    val name: String?,
    val picture: String?,
    val pictureBig: String?,
    val pictureMedium: String?,
    val pictureSmall: String?,
    val pictureXl: String?,
    val type: String?
):Parcelable
