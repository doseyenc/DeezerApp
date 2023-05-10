package com.example.deezerapp.artistdetailpage.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingData
import com.example.deezerapp.artistdetailpage.domain.usecase.AlbumListingUseCase
import com.example.deezerapp.artistdetailpage.ui.viewstate.AlbumListingViewState
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.deezerapp.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class AlbumListingViewModel @Inject constructor(
    private val albumListingUseCase: AlbumListingUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<AlbumListingViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<AlbumListingViewState> = stateLiveData


    fun getAlbumList(
        id : String,
    ) {
        albumListingUseCase
            .getAlbumList(
                id = id
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetAlbumListResponseReady(it)
                },
                onLoading = {
                    onGetAlbumListLoading()
                },
                onError = {
                    onGetAlbumListResponseFail(it)
                }
            )
    }

    private fun onGetAlbumListResponseReady(albumListingData: AlbumListingData) {
        stateLiveData.value = AlbumListingViewState.Success(albumListingData)
    }

    private fun onGetAlbumListLoading() {
        stateLiveData.value = AlbumListingViewState.Loading
    }

    private fun onGetAlbumListResponseFail(throwable: Throwable) {
        stateLiveData.value = AlbumListingViewState.Error(throwable)
    }


}