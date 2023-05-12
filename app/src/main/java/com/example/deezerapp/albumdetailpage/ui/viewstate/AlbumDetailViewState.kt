package com.example.deezerapp.albumdetailpage.ui.viewstate

import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData

sealed class AlbumDetailViewState {
    object Loading : AlbumDetailViewState()
    object Empty : AlbumDetailViewState()
    data class Success(val albumDetailData: AlbumDetailData?) : AlbumDetailViewState()
    data class SuccessLocalList(val albumDetailDataList: List<TrackDomainDataData?>?) : AlbumDetailViewState()
    data class SuccessLocal(val albumDetailData: TrackDomainDataData?) : AlbumDetailViewState()
    data class Error(val throwable: Throwable) : AlbumDetailViewState()
}
