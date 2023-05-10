package com.example.deezerapp.artistdetailpage.ui


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.deezerapp.artistdetailpage.domain.model.ArtistDetailData
import com.example.deezerapp.artistdetailpage.ui.viewmodel.ArtistDetailViewModel
import com.example.deezerapp.artistdetailpage.ui.viewstate.ArtistDetailViewState
import com.example.deezerapp.artistlistingpage.ui.viewstate.ArtistListingViewState
import com.example.deezerapp.common.util.createPlaceHolder
import com.example.deezerapp.common.util.setImage
import com.example.deezerapp.common.view.BaseFragment
import com.example.deezerapp.databinding.FragmentArtistDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : BaseFragment<FragmentArtistDetailBinding>() {
    //ViewModel
    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()

    //args
    private val args: ArtistDetailFragmentArgs by navArgs()
    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        with(artistDetailViewModel) {
            getArtistDetail(
                id = args.ArtistListingDataData.id.toString(),
            )
            getStateLiveData().observe(viewLifecycleOwner){
                renderGetArtistDetailStatusViewState(it)
            }

        }
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


    }

}