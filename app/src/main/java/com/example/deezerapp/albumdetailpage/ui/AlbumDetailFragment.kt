package com.example.deezerapp.albumdetailpage.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezerapp.albumdetailpage.AudioPlayer
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
import com.example.deezerapp.albumdetailpage.ui.adapter.MusicAdapter
import com.example.deezerapp.albumdetailpage.ui.viewmodel.AlbumDetailViewModel
import com.example.deezerapp.albumdetailpage.ui.viewstate.AlbumDetailViewState
import com.example.deezerapp.common.view.BaseFragment
import com.example.deezerapp.databinding.FragmentAlbumDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailFragment : BaseFragment<FragmentAlbumDetailBinding>() {
    //ViewModel
    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    private val args: AlbumDetailFragmentArgs by navArgs()

    //Adapter
    @Inject
    lateinit var musicAdapter: MusicAdapter

    private val audioPlayer = AudioPlayer()

    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        with(albumDetailViewModel) {
            getAlbumDetail(args.albumId)
            getAllTracks()
            getStateLiveData().observe(viewLifecycleOwner) {
                renderAlbumDetailStatusViewState(it)
            }
        }
    }

    private fun renderAlbumDetailStatusViewState(viewState: AlbumDetailViewState) =
        when (viewState) {
            is AlbumDetailViewState.Loading -> loadingInProgressAlbumDetail()
            is AlbumDetailViewState.Empty -> emptyStateAlbumDetail()
            is AlbumDetailViewState.Success -> displayAlbumDetailData(viewState.albumDetailData)
            is AlbumDetailViewState.SuccessLocal -> displayLocalAlbumDetailData(viewState.albumDetailData)
            is AlbumDetailViewState.SuccessLocalList -> displayLocalListAlbumDetailData(viewState.albumDetailDataList)
            is AlbumDetailViewState.Error -> errorHandleAlbumDetail(viewState.throwable)
        }


    private fun displayLocalListAlbumDetailData(albumDetailDataList: List<TrackDomainDataData?>?) {
        Log.e("AlbumDetailFragment", "displayLocalListAlbumDetailData: $albumDetailDataList")
        val trackIdList: List<Long> = albumDetailDataList?.map { track ->
            track?.trackId ?: 0L
        } ?: emptyList()
        musicAdapter.getIdList(trackIdList as MutableList<Long>)
    }

    private fun displayLocalAlbumDetailData(albumDetailDataList: TrackDomainDataData?) {
        Log.e("AlbumDetailFragment", "displayLocalAlbumDetailData: $albumDetailDataList")
    }

    private fun errorHandleAlbumDetail(throwable: Throwable) {
        Log.e("AlbumDetailFragment", "errorHandleAlbumDetail: $throwable")
        binding.albumDetailStateLayout.loading()
    }

    private fun displayAlbumDetailData(albumDetailData: AlbumDetailData?) {
        Log.e("AlbumDetailFragment", "displayAlbumDetailData: $albumDetailData")
        with(binding) {
            albumDetailStateLayout.content()
            textViewAlbumName.text = albumDetailData?.title
            albumDetailData?.tracks?.data?.let { musicAdapter.setItems(it) }
        }
    }

    private fun emptyStateAlbumDetail() {
        Log.e("AlbumDetailFragment", "emptyStateAlbumDetail: ")
        binding.albumDetailStateLayout.loading()
    }

    private fun loadingInProgressAlbumDetail() {
        Log.e("AlbumDetailFragment", "loadingInProgressAlbumDetail: ")
        binding.albumDetailStateLayout.loading()
    }

    private fun setupView() {
        setUpRv()
        musicAdapter.onMusicClick = {
            Log.e("AlbumDetailFragment", "setupView: $it")
            audioPlayer.stopAudio()
            it.preview?.let { it1 -> audioPlayer.playAudio(it1) }
        }
        musicAdapter.onMusicSaveClick = { music: TrackDomainDataData, saved: Boolean, pos: Int ->
            val viewHolder =
                binding.recyclerViewSongs.findViewHolderForAdapterPosition(pos) as MusicAdapter.MusicItemViewHolder

            if (saved) {
                albumDetailViewModel.deleteMusic(music)
                viewHolder.setUnSavedBg(requireContext())
                navigate(AlbumDetailFragmentDirections.actionAlbumDetailFragmentSelf(args.albumId))
            } else {
                albumDetailViewModel.saveMusic(music)
                viewHolder.setSavedBg(requireContext())
                navigate(AlbumDetailFragmentDirections.actionAlbumDetailFragmentSelf(args.albumId))
            }


        }
    }

    private fun setUpRv() {
        musicAdapter = MusicAdapter()
        with(binding.recyclerViewSongs) {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = musicAdapter
        }
    }

    override fun onStop() {
        super.onStop()
        audioPlayer.stopAudio()
    }

}
