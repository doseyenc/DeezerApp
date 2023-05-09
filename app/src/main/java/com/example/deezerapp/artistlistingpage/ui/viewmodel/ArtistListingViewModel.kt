package com.example.deezerapp.artistlistingpage.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingData
import com.example.deezerapp.artistlistingpage.domain.usecase.ArtistListingUseCase
import com.example.deezerapp.artistlistingpage.ui.viewstate.ArtistListingViewState
import com.example.deezerapp.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.subscribe
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


@HiltViewModel
class ArtistListingViewModel @Inject constructor(
    private val artistListingUseCase: ArtistListingUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<ArtistListingViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<ArtistListingViewState> = stateLiveData


    fun getArtistList(
        id : String,
    ) {
        artistListingUseCase
            .getArtistList(
                id = id
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetArtistListResponseReady(it)
                },
                onLoading = {
                    onGetArtistListLoading()
                },
                onError = {
                    onGetArtistListResponseFail(it)
                }
            )
    }

    private fun onGetArtistListResponseReady(movieDetailData: ArtistListingData) {
        stateLiveData.value = ArtistListingViewState.Success(movieDetailData)
    }

    private fun onGetArtistListLoading() {
        stateLiveData.value = ArtistListingViewState.Loading
    }

    private fun onGetArtistListResponseFail(throwable: Throwable) {
        stateLiveData.value = ArtistListingViewState.Error(throwable)
    }
}