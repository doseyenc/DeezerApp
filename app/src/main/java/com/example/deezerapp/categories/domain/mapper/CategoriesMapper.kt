package com.example.deezerapp.categories.domain.mapper

import com.example.deezerapp.categories.data.model.CategoriesResponse
import com.example.deezerapp.categories.data.model.CategoriesResponseData
import com.example.deezerapp.categories.domain.model.CategoriesData
import com.example.deezerapp.categories.domain.model.CategoriesDataData
import javax.inject.Inject

class CategoriesMapper @Inject constructor() {

    fun mapResultsFromResponse(
        response: CategoriesResponse?
    ): CategoriesData {
        return CategoriesData(
            data = mapFromResponseToData(response?.data)
        )
    }

    private fun mapFromResponseToData(categoryList: List<CategoriesResponseData?>?): List<CategoriesDataData?> {
        return categoryList?.mapNotNull {
            mapCategoryList(it)
        }?.filter {
            it.id != null
        }.orEmpty()
    }

    private fun mapCategoryList(category: CategoriesResponseData?): CategoriesDataData {
        return CategoriesDataData(
            id = category?.id,
            name = category?.name,
            picture = category?.picture,
            pictureSmall = category?.pictureSmall,
            pictureMedium = category?.pictureMedium,
            pictureBig = category?.pictureBig,
            pictureXl = category?.pictureXl,
            type = category?.type
        )
    }
}