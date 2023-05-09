package com.example.deezerapp.artistlistingpage.ui.viewstate

import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingData

sealed class ArtistListingViewState {
    object Loading : ArtistListingViewState()
    object Empty : ArtistListingViewState()
    data class Success(val artistListingData: ArtistListingData?) : ArtistListingViewState()
    data class Error(val throwable: Throwable) : ArtistListingViewState()
}
