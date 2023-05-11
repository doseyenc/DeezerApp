package com.example.deezerapp.albumdetailpage.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.domain.usecase.AlbumDetailUseCase
import com.example.deezerapp.albumdetailpage.ui.viewstate.AlbumDetailViewState
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.deezerapp.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val albumDetailUseCase: AlbumDetailUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<AlbumDetailViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<AlbumDetailViewState> = stateLiveData


    fun getAlbumDetail(
        id : String,
    ) {
        albumDetailUseCase
            .getAlbumDetail(
                id = id
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetAlbumDetailResponseReady(it)
                },
                onLoading = {
                    onGetAlbumDetailLoading()
                },
                onError = {
                    onGetAlbumDetailResponseFail(it)
                }
            )
    }

    private fun onGetAlbumDetailResponseReady(albumDetailData: AlbumDetailData) {
        stateLiveData.value = AlbumDetailViewState.Success(albumDetailData)
    }

    private fun onGetAlbumDetailLoading() {
        stateLiveData.value = AlbumDetailViewState.Loading
    }

    private fun onGetAlbumDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = AlbumDetailViewState.Error(throwable)
    }


}