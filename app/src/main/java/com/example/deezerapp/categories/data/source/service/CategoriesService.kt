package com.example.deezerapp.categories.data.source.service


import com.example.deezerapp.categories.data.model.CategoriesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CategoriesService {

    @GET("genre")
    fun getCategories(): Single<CategoriesResponse>

}