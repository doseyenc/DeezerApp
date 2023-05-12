package com.example.deezerapp.savedpage


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezerapp.albumdetailpage.AudioPlayer
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
import com.example.deezerapp.albumdetailpage.ui.AlbumDetailFragmentDirections
import com.example.deezerapp.albumdetailpage.ui.adapter.MusicAdapter
import com.example.deezerapp.albumdetailpage.ui.viewmodel.AlbumDetailViewModel
import com.example.deezerapp.albumdetailpage.ui.viewstate.AlbumDetailViewState
import com.example.deezerapp.common.view.BaseFragment
import com.example.deezerapp.databinding.FragmentSavedBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>() {
    private val audioPlayer = AudioPlayer()
    private val viewModel: AlbumDetailViewModel by viewModels()

    @Inject
    lateinit var musicAdapter: MusicAdapter
    override fun init() {
        setupView()
        setupViewModel()
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
                binding.recyclerViewSavedSongs.findViewHolderForAdapterPosition(pos) as MusicAdapter.MusicItemViewHolder
            viewModel.deleteMusic(music)
            viewHolder.setUnSavedBg(requireContext())
            navigate(SavedFragmentDirections.actionSavedFragmentSelf())
        }
    }

    private fun setUpRv() {
        musicAdapter = MusicAdapter()
        with(binding.recyclerViewSavedSongs) {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = musicAdapter
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            getAllTracks()
            getStateLiveData().observe(viewLifecycleOwner) {
                renderAlbumDetailStatusViewState(it)
            }
        }


    }

    private fun renderAlbumDetailStatusViewState(viewState: AlbumDetailViewState?) =
        when (viewState) {
            is AlbumDetailViewState.Loading -> loadingInProgressSavedTracks()
            is AlbumDetailViewState.Empty -> emptyStateSavedTracks()
            is AlbumDetailViewState.Error -> errorHandleSavedTracks(viewState.throwable)
            is AlbumDetailViewState.SuccessLocalList -> displayLocalListAlbumDetailData(viewState.albumDetailDataList)
            else -> {}
        }

    private fun displayLocalListAlbumDetailData(albumDetailDataList: List<TrackDomainDataData?>?) {
        Log.e("AlbumDetailFragment", "displayLocalListAlbumDetailData: $albumDetailDataList")
        binding.savedSongsStateLayout.content()
        val trackIdList: List<Int> = albumDetailDataList?.map { track ->
            track?.trackId ?: 0
        } ?: emptyList()
        musicAdapter.getIdList(trackIdList as MutableList<Int>)
        if (trackIdList.isNotEmpty()) {
            musicAdapter.setItems(albumDetailDataList as List<TrackDomainDataData>)
        } else {
            binding.recyclerViewSavedSongs.visibility = android.view.View.GONE
            binding.textViewEmpty.visibility = android.view.View.VISIBLE
        }
    }

    private fun loadingInProgressSavedTracks() {
        Log.e("SavedFragment", "loadingInProgressSavedTracks: ")
        binding.savedSongsStateLayout.loading()
    }

    private fun emptyStateSavedTracks() {
        Log.e("SavedFragment", "emptyStateSavedTracks: ")
        binding.savedSongsStateLayout.loading()
    }

    private fun errorHandleSavedTracks(throwable: Throwable) {
        Log.e("SavedFragment", "errorHandleSavedTracks: $throwable")
        binding.savedSongsStateLayout.loading()
    }

}
