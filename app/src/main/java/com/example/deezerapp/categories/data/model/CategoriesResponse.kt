package com.example.deezerapp.categories.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val data: List<CategoriesResponseData?>?,

)
