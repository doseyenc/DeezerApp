package com.example.deezerapp.categories.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deezerapp.categories.domain.model.CategoriesData
import com.example.deezerapp.categories.domain.usecase.CategoriesUseCase
import com.example.deezerapp.categories.ui.viewstate.CategoriesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import com.example.deezerapp.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.deezerapp.common.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<CategoriesViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<CategoriesViewState> = stateLiveData

    fun getCategories() {
        categoriesUseCase
            .getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetCategoriesResponseReady(it)
                },
                onLoading = {
                    onGetCategoriesLoading()
                },
                onError = {
                    onGetCategoriesResponseFail(it)
                }
            )
    }

    private fun onGetCategoriesResponseReady(categoriesData: CategoriesData) {
        stateLiveData.value = CategoriesViewState.Success(categoriesData)
    }

    private fun onGetCategoriesLoading() {
        stateLiveData.value = CategoriesViewState.Loading
    }

    private fun onGetCategoriesResponseFail(throwable: Throwable) {
        stateLiveData.value = CategoriesViewState.Error(throwable)
    }
}