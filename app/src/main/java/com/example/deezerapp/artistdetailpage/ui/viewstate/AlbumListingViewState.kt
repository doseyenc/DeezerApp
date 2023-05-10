package com.example.deezerapp.artistdetailpage.ui.viewstate

import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingData

sealed class AlbumListingViewState {
    object Loading : AlbumListingViewState()
    object Empty : AlbumListingViewState()
    data class Success(val albumListingData: AlbumListingData?) : AlbumListingViewState()
    data class Error(val throwable: Throwable) : AlbumListingViewState()
}
