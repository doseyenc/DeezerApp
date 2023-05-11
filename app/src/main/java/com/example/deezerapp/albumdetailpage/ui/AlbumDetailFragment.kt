package com.example.deezerapp.albumdetailpage.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezerapp.albumdetailpage.domain.model.AlbumDetailData
import com.example.deezerapp.albumdetailpage.ui.adapter.MusicAdapter
import com.example.deezerapp.albumdetailpage.ui.viewmodel.AlbumDetailViewModel
import com.example.deezerapp.albumdetailpage.ui.viewstate.AlbumDetailViewState
import com.example.deezerapp.artistdetailpage.ui.adapter.AlbumListingAdapter
import com.example.deezerapp.artistdetailpage.ui.viewstate.AlbumListingViewState
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

    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        with(albumDetailViewModel) {
            getAlbumDetail(args.albumId)
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
            is AlbumDetailViewState.Error -> errorHandleAlbumDetail(viewState.throwable)
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
        }
    }

    private fun setUpRv() {
        musicAdapter = MusicAdapter()
        with(binding.recyclerViewSongs) {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = musicAdapter
        }
    }

}
