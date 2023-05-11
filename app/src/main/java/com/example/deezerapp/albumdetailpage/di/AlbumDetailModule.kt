package com.example.deezerapp.albumdetailpage.di

import com.example.deezerapp.albumdetailpage.data.source.AlbumDetailDataSource
import com.example.deezerapp.albumdetailpage.data.source.remote.AlbumDetailRemoteDataSource
import com.example.deezerapp.albumdetailpage.data.source.service.AlbumDetailService
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
class AlbumDetailModule {
    @Singleton
    @Provides
    fun provideAlbumDetailService(): AlbumDetailService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AlbumDetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumDetailRemoteDataSourceBuilder(): AlbumDetailDataSource.Remote {
        return AlbumDetailRemoteDataSource(provideAlbumDetailService())
    }

}