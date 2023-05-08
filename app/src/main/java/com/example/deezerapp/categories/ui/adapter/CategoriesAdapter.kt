package com.example.deezerapp.categories.ui.adapter

import com.example.deezerapp.categories.domain.model.CategoriesDataData
import com.example.deezerapp.common.adapter.BaseRecyclerViewAdapter
import com.example.deezerapp.common.util.inflate
import com.example.deezerapp.databinding.MusicCategoriesItemDesignBinding
import android.view.ViewGroup
import com.example.deezerapp.common.util.createPlaceHolder
import com.example.deezerapp.common.util.setImage
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<CategoriesDataData,
            MusicCategoriesItemDesignBinding>() {
    var onCategoriesClick: ((trending: CategoriesDataData) -> Unit)? = null

    inner class TrendingItemViewHolder(private val binding: MusicCategoriesItemDesignBinding) :
        ViewBindingViewHolder(binding) {
        override fun bind(item: CategoriesDataData) {
            with(binding) {
                imageViewCategoryImage.setImage(
                    url = item.pictureMedium,
                    placeholder = createPlaceHolder(root.context)
                )
                textViewCategoryTitle.text = item.name
                cardViewCategory.setOnClickListener {
                    onCategoriesClick?.invoke(item)
                }
            }
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return TrendingItemViewHolder(parent.inflate(MusicCategoriesItemDesignBinding::inflate))
    }
}