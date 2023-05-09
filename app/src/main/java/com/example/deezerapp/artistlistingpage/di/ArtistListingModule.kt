package com.example.deezerapp.artistlistingpage.di

import com.example.deezerapp.artistlistingpage.data.source.ArtistListingDataSource
import com.example.deezerapp.artistlistingpage.data.source.remote.ArtistListingRemoteDataSource
import com.example.deezerapp.artistlistingpage.data.source.service.ArtistListingService
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
class ArtistListingModule {
    @Singleton
    @Provides
    fun provideArtistListingService(): ArtistListingService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ArtistListingService::class.java)
    }

    @Singleton
    @Provides
    fun provideArtistListingRemoteDataSourceBuilder(): ArtistListingDataSource.Remote {
        return ArtistListingRemoteDataSource(provideArtistListingService())
    }

}