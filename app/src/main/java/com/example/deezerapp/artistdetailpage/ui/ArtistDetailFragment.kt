package com.example.deezerapp.artistdetailpage.ui


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingData
import com.example.deezerapp.artistdetailpage.domain.model.ArtistDetailData
import com.example.deezerapp.artistdetailpage.ui.adapter.AlbumListingAdapter
import com.example.deezerapp.artistdetailpage.ui.viewmodel.AlbumListingViewModel
import com.example.deezerapp.artistdetailpage.ui.viewmodel.ArtistDetailViewModel
import com.example.deezerapp.artistdetailpage.ui.viewstate.AlbumListingViewState
import com.example.deezerapp.artistdetailpage.ui.viewstate.ArtistDetailViewState
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingDataData
import com.example.deezerapp.artistlistingpage.ui.adapter.ArtistListingAdapter
import com.example.deezerapp.artistlistingpage.ui.viewstate.ArtistListingViewState
import com.example.deezerapp.common.util.createPlaceHolder
import com.example.deezerapp.common.util.setImage
import com.example.deezerapp.common.view.BaseFragment
import com.example.deezerapp.databinding.FragmentArtistDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistDetailFragment : BaseFragment<FragmentArtistDetailBinding>() {
    //ViewModel
    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()
    private val albumListingViewModel: AlbumListingViewModel by viewModels()

    //args
    private val args: ArtistDetailFragmentArgs by navArgs()

    //Adapter
    @Inject
    lateinit var albumListingAdapter: AlbumListingAdapter
    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        with(artistDetailViewModel) {
            getArtistDetail(
                id = args.ArtistListingDataData.id.toString(),
            )
            getStateLiveData().observe(viewLifecycleOwner) {
                renderGetArtistDetailStatusViewState(it)
            }

        }
        with(albumListingViewModel) {
            getAlbumList(
                id = args.ArtistListingDataData.id.toString(),
            )
            getStateLiveData().observe(viewLifecycleOwner) {
                renderGetAlbumListingStatusViewState(it)
            }
        }
    }

    private fun renderGetAlbumListingStatusViewState(viewState: AlbumListingViewState) =
        when (viewState) {
            is AlbumListingViewState.Loading -> loadingInProgressAlbumListing()
            is AlbumListingViewState.Empty -> emptyStateAlbumListing()
            is AlbumListingViewState.Success -> displayAlbumListingData(viewState.albumListingData)
            is AlbumListingViewState.Error -> errorHandleAlbumListing(viewState.throwable)
        }

    private fun errorHandleAlbumListing(throwable: Throwable) {
        binding.artistDetailStateLayout.loading()
        Log.e("ArtistDetailFragment", "errorHandle: ${throwable.message}")
    }

    private fun displayAlbumListingData(albumListingData: AlbumListingData?) {
        Log.e("ArtistDetailFragment", "displayData: $albumListingData")
        with(binding) {
            artistDetailStateLayout.content()
            albumListingData?.data?.let { albumListingAdapter.setItems(it) }
        }
    }

    private fun emptyStateAlbumListing() {
        binding.artistDetailStateLayout.loading()
        Log.e("ArtistDetailFragment", "emptyState: ")
    }

    private fun loadingInProgressAlbumListing() {
        binding.artistDetailStateLayout.loading()
        Log.e("ArtistDetailFragment", "loading: ")
    }

    private fun renderGetArtistDetailStatusViewState(viewState: ArtistDetailViewState) =
        when (viewState) {
            is ArtistDetailViewState.Loading -> loadingInProgress()
            is ArtistDetailViewState.Empty -> emptyState()
            is ArtistDetailViewState.Success -> displayData(viewState.artistDetailData)
            is ArtistDetailViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun errorHandle(throwable: Throwable) {
        binding.artistDetailStateLayout.loading()
        Log.e("ArtistDetailFragment", "errorHandle: ${throwable.message}")
    }

    private fun displayData(artistDetailData: ArtistDetailData?) {
        Log.e("ArtistDetailFragment", "displayData: ${artistDetailData}")
        with(binding) {
            artistDetailStateLayout.content()
            textViewArtistName.text = artistDetailData?.name
            imageViewArtistPhoto.setImage(
                url = artistDetailData?.pictureXl,
                placeholder = createPlaceHolder(requireContext())
            )
        }
    }


    private fun emptyState() {
        binding.artistDetailStateLayout.loading()
        Log.e("ArtistDetailFragment", "emptyState: ")
    }

    private fun loadingInProgress() {
        binding.artistDetailStateLayout.loading()
        Log.e("ArtistDetailFragment", "loadingInProgress: ")
    }

    private fun setupView() {
        setUpRv()
        albumListingAdapter.onAlbumClick = {
            Log.e("ArtistDetailFragment", "onAlbumClick: $it")
            navigate(
                ArtistDetailFragmentDirections.actionArtistDetailFragmentToAlbumDetailFragment(
                    it.id.toString()
                )
            )
        }
    }

    private fun setUpRv() {
        albumListingAdapter = AlbumListingAdapter()
        with(binding.recyclerViewArtistAlbums) {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = albumListingAdapter
        }
    }


}