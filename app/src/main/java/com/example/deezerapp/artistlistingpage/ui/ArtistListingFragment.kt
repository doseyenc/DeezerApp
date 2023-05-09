package com.example.deezerapp.artistlistingpage.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deezerapp.R
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingData
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingDataData
import com.example.deezerapp.artistlistingpage.ui.adapter.ArtistListingAdapter
import com.example.deezerapp.artistlistingpage.ui.viewmodel.ArtistListingViewModel
import com.example.deezerapp.artistlistingpage.ui.viewstate.ArtistListingViewState
import com.example.deezerapp.categories.domain.model.CategoriesData
import com.example.deezerapp.categories.domain.model.CategoriesDataData
import com.example.deezerapp.categories.ui.adapter.CategoriesAdapter
import com.example.deezerapp.categories.ui.viewmodel.CategoriesViewModel
import com.example.deezerapp.categories.ui.viewstate.CategoriesViewState
import com.example.deezerapp.common.view.BaseFragment
import com.example.deezerapp.databinding.FragmentArtistListingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistListingFragment : BaseFragment<FragmentArtistListingBinding>() {

    //Adapter
    @Inject
    lateinit var artistListingAdapter: ArtistListingAdapter

    //ViewModel
    private val artistListingViewModel: ArtistListingViewModel by viewModels()

    private val args: ArtistListingFragmentArgs by navArgs()

    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        setUpRv()
        with(binding) {
            textViewCategoryTitle.text = args.CategoriesDataData.name
        }
        artistListingAdapter.onCategoriesClick = {
            Log.e(TAG, "categoryClicked: $it")
        }
    }

    private fun setUpRv() {
        artistListingAdapter = ArtistListingAdapter()
        with(binding.artistsRecyclerView) {
            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2,
                    androidx.recyclerview.widget.GridLayoutManager.VERTICAL,
                    false
                )
            adapter = artistListingAdapter
        }
    }

    private fun setupViewModel() {
        with(artistListingViewModel) {
            getArtistList(
                id = args.CategoriesDataData.id.toString()
            )
            getStateLiveData().observe(viewLifecycleOwner) {
                renderStatusCategoriesViewState(it)
            }
        }
    }

    private fun renderStatusCategoriesViewState(viewState: ArtistListingViewState) =
        when (viewState) {
            is ArtistListingViewState.Loading -> loadingInProgress()
            is ArtistListingViewState.Empty -> emptyState()
            is ArtistListingViewState.Success -> displayData(viewState.artistListingData)
            is ArtistListingViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun displayData(artistListingData: ArtistListingData?) {
        binding.artistsStateLayout.content()
        artistListingData?.data?.let { artistListingAdapter.setItems(it as List<ArtistListingDataData>) }
    }

    private fun errorHandle(throwable: Throwable) {
        binding.artistsStateLayout.loading()
        Log.e(TAG, "error: " + throwable.message)
    }

    private fun emptyState() {
        binding.artistsStateLayout.loading()
        Log.e(TAG, "emptyState: ")
    }

    private fun loadingInProgress() {
        binding.artistsStateLayout.loading()
        Log.e(TAG, "loadingInProgress: ")
    }

    companion object {
        val TAG = "CategoriesFragment"
    }
}