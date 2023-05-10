package com.example.deezerapp.artistdetailpage.di

import com.example.deezerapp.artistdetailpage.data.model.AlbumListingResponse
import com.example.deezerapp.artistdetailpage.data.source.AlbumListingDataSource
import com.example.deezerapp.artistdetailpage.data.source.ArtistDetailDataSource
import com.example.deezerapp.artistdetailpage.data.source.remote.AlbumListingRemoteDataSource
import com.example.deezerapp.artistdetailpage.data.source.remote.ArtistDetailRemoteDataSource
import com.example.deezerapp.artistdetailpage.data.source.service.AlbumListingService
import com.example.deezerapp.artistdetailpage.data.source.service.ArtistDetailService
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
class AlbumListingModule {
    @Singleton
    @Provides
    fun provideAlbumListingService(): AlbumListingService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AlbumListingService::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumListingRemoteDataSourceBuilder(): AlbumListingDataSource.Remote {
        return AlbumListingRemoteDataSource(provideAlbumListingService())
    }

}