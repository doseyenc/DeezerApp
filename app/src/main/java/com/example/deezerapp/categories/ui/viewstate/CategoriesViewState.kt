package com.example.deezerapp.categories.ui.viewstate

import com.example.deezerapp.categories.domain.model.CategoriesData

sealed class CategoriesViewState {
    object Loading : CategoriesViewState()
    object Empty : CategoriesViewState()
    data class Success(val addCityPlaceSearchData: CategoriesData?) : CategoriesViewState()
    data class Error(val throwable: Throwable) : CategoriesViewState()
}
