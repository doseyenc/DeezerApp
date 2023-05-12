package com.example.deezerapp.albumdetailpage.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezerapp.albumdetailpage.data.model.local.AlbumLocalData
import com.example.deezerapp.albumdetailpage.data.model.local.TrackLocalData
import com.example.deezerapp.albumdetailpage.domain.model.AlbumData
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
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
        id: String,
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

    fun getAllTracks(
    ) {
        albumDetailUseCase
            .getAllTracks(
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetAlbumListDetailResponseReady(it)
                },
                onLoading = {
                    onGetAlbumDetailLoading()
                },
                onError = {
                    onGetAlbumDetailResponseFail(it)
                }
            )
    }

    fun getTrackById(
        id: String
    ) {
        albumDetailUseCase
            .getTrackById(
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

    fun saveMusic(
        trackDomainDataData: TrackDomainDataData
    ) {
        albumDetailUseCase
            .saveTrack(
                trackLocalData = createTrackToSave(trackDomainDataData)
            )
    }

    private fun createTrackToSave(trackDomainDataData: TrackDomainDataData): TrackLocalData {
        return TrackLocalData(
            musicId = trackDomainDataData.trackId,
            titleShort = trackDomainDataData.title,
            duration = trackDomainDataData.duration,
            preview = trackDomainDataData.preview,
            link = trackDomainDataData.link,
            trackTitle = trackDomainDataData.title,
            album = createAlbumToSave(trackDomainDataData.album)

        )

    }

    private fun createAlbumToSave(album: AlbumData?): AlbumLocalData? {
        return album?.let {
            AlbumLocalData(
                cover = it.cover,
                coverSmall = it.coverSmall,
                coverMedium = it.coverMedium,
                coverBig = it.coverBig,
                coverXl = it.coverXl,
                trackList = it.trackList,
                type = it.type,
                id = it.id,
                albumTitle = it.title,
            )
        }
    }

    private fun onGetAlbumDetailResponseReady(albumDetailData: AlbumDetailData) {
        stateLiveData.value = AlbumDetailViewState.Success(albumDetailData)
    }

    private fun onGetAlbumListDetailResponseReady(albumDetailDataList: List<TrackDomainDataData?>?) {
        stateLiveData.value = AlbumDetailViewState.SuccessLocalList(albumDetailDataList)
    }

    private fun onGetAlbumDetailResponseReady(track: TrackDomainDataData?) {
        stateLiveData.value = AlbumDetailViewState.SuccessLocal(track)
    }

    private fun onGetAlbumDetailLoading() {
        stateLiveData.value = AlbumDetailViewState.Loading
    }

    private fun onGetAlbumDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = AlbumDetailViewState.Error(throwable)
    }


}