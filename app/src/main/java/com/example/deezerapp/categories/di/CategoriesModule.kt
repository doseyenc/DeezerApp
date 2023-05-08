package com.example.deezerapp.categories.di

import com.example.deezerapp.categories.data.source.CategoriesDataSource
import com.example.deezerapp.categories.data.source.remote.CategoriesRemoteDataSource
import com.example.deezerapp.categories.data.source.service.CategoriesService
import com.example.deezerapp.common.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CategoriesModule {
    @Singleton
    @Provides
    fun provideDetailService(): CategoriesService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CategoriesService::class.java)
    }
    @Singleton
    @Provides
    fun provideCategoriesRemoteDataSourceBuilder(): CategoriesDataSource.Remote {
        return CategoriesRemoteDataSource(provideDetailService())
    }

}