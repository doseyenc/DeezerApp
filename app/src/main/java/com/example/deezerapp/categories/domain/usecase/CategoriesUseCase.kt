package com.example.deezerapp.categories.domain.usecase

import com.example.deezerapp.categories.data.repository.CategoriesRepository
import com.example.deezerapp.categories.domain.mapper.CategoriesMapper
import com.example.deezerapp.categories.domain.model.CategoriesData
import com.example.deezerapp.common.extensions.Resource
import com.example.deezerapp.common.extensions.mapOnData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val categoriesMapper: CategoriesMapper
) {

    fun getCategories(): Observable<Resource<CategoriesData>> {
        return categoriesRepository
            .getCategories().mapOnData {
                categoriesMapper.mapResultsFromResponse(it)
            }
    }
}