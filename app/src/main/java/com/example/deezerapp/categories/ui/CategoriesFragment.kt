package com.example.deezerapp.categories.ui


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezerapp.categories.domain.model.CategoriesData
import com.example.deezerapp.categories.domain.model.CategoriesDataData
import com.example.deezerapp.categories.ui.adapter.CategoriesAdapter
import com.example.deezerapp.categories.ui.viewmodel.CategoriesViewModel
import com.example.deezerapp.categories.ui.viewstate.CategoriesViewState
import com.example.deezerapp.common.view.BaseFragment
import com.example.deezerapp.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    private val categoriesViewModel: CategoriesViewModel by viewModels()
    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        setUpRv()
        categoriesAdapter.onCategoriesClick = {
            Log.e(TAG, "categoryClicked: $it")
        }
    }

    private fun setUpRv() {
        categoriesAdapter = CategoriesAdapter()
        with(binding.categoriesRecyclerView) {
            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                )
            categoriesAdapter.onCategoriesClick = { it ->

            }
            adapter = categoriesAdapter
        }
    }

    private fun setupViewModel() {
        with(categoriesViewModel) {
            getCategories()
            getStateLiveData().observe(viewLifecycleOwner) {
                renderStatusCategoriesViewState(it)
            }
        }
    }

    private fun renderStatusCategoriesViewState(viewState: CategoriesViewState) =
        when (viewState) {
            is CategoriesViewState.Loading -> loadingInProgress()
            is CategoriesViewState.Empty -> emptyState()
            is CategoriesViewState.Success -> displayData(viewState.categoriesData)
            is CategoriesViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun displayData(categoriesData: CategoriesData?) {
        binding.categoriesStateLayout.content()
        categoriesData?.data?.let { categoriesAdapter.setItems(it as List<CategoriesDataData>) }
    }

    private fun errorHandle(throwable: Throwable) {
        binding.categoriesStateLayout.loading()
        Log.e(TAG, "error: " + throwable.message)
    }

    private fun emptyState() {
        binding.categoriesStateLayout.loading()
        Log.e(TAG, "emptyState: ")
    }

    private fun loadingInProgress() {
        binding.categoriesStateLayout.loading()
        Log.e(TAG, "loadingInProgress: ")
    }

    companion object {
        val TAG = "CategoriesFragment"
    }
}