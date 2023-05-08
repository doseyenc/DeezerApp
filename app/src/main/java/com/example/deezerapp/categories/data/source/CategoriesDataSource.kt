package com.example.deezerapp.categories.data.source

import com.example.deezerapp.categories.data.model.CategoriesResponse
import io.reactivex.rxjava3.core.Observable

interface CategoriesDataSource {
    interface Remote {
        fun getCategories(): Observable<CategoriesResponse>
    }
}