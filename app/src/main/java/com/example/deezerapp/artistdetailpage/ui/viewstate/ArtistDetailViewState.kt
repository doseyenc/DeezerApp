package com.example.deezerapp.artistdetailpage.ui.viewstate

import com.example.deezerapp.artistdetailpage.domain.model.ArtistDetailData

sealed class ArtistDetailViewState {
    object Loading : ArtistDetailViewState()
    object Empty : ArtistDetailViewState()
    data class Success(val artistDetailData: ArtistDetailData?) : ArtistDetailViewState()
    data class Error(val throwable: Throwable) : ArtistDetailViewState()
}
