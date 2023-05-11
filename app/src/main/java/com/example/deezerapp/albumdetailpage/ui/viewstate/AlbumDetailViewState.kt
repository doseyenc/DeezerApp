package com.example.deezerapp.albumdetailpage.ui.viewstate

import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData

sealed class AlbumDetailViewState {
    object Loading : AlbumDetailViewState()
    object Empty : AlbumDetailViewState()
    data class Success(val albumDetailData: AlbumDetailData?) : AlbumDetailViewState()
    data class Error(val throwable: Throwable) : AlbumDetailViewState()
}
