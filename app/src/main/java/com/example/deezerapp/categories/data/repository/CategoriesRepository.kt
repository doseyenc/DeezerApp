package com.example.deezerapp.categories.data.repository

import com.example.deezerapp.categories.data.model.CategoriesResponse
import com.example.deezerapp.categories.data.source.CategoriesDataSource
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.remote
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesDataSource: CategoriesDataSource.Remote
) {
    fun getCategories(): Observable<Resource<CategoriesResponse>> {
        return categoriesDataSource
            .getCategories()
            .remote()
    }
}