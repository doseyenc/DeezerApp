package com.example.deezerapp.categories.data.source.remote

import com.example.deezerapp.categories.data.model.CategoriesResponse
import com.example.deezerapp.categories.data.source.CategoriesDataSource
import com.example.deezerapp.categories.data.source.service.CategoriesService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CategoriesRemoteDataSource @Inject constructor(
    private val categoriesService: CategoriesService
) : CategoriesDataSource.Remote {
    override fun getCategories(): Observable<CategoriesResponse> {
        return categoriesService.getCategories().toObservable()
    }
}