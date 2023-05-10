package com.example.deezerapp.artistdetailpage.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezerapp.artistdetailpage.domain.model.ArtistDetailData
import com.example.deezerapp.artistdetailpage.domain.usecase.ArtistDetailUseCase
import com.example.deezerapp.artistdetailpage.ui.viewstate.ArtistDetailViewState
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.deezerapp.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val artistDetailUseCase: ArtistDetailUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<ArtistDetailViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<ArtistDetailViewState> = stateLiveData


    fun getArtistDetail(
        id : String,
    ) {
        artistDetailUseCase
            .getArtistDetail(
                id = id
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetArtistDetailResponseReady(it)
                },
                onLoading = {
                    onGetArtistDetailLoading()
                },
                onError = {
                    onGetArtistDetailResponseFail(it)
                }
            )
    }

    private fun onGetArtistDetailResponseReady(movieDetailData: ArtistDetailData) {
        stateLiveData.value = ArtistDetailViewState.Success(movieDetailData)
    }

    private fun onGetArtistDetailLoading() {
        stateLiveData.value = ArtistDetailViewState.Loading
    }

    private fun onGetArtistDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = ArtistDetailViewState.Error(throwable)
    }


}