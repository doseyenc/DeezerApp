package com.example.deezerapp.artistdetailpage.ui.adapter

import android.view.ViewGroup
import com.example.deezerapp.artistdetailpage.domain.model.AlbumListingDataData
import com.example.deezerapp.artistlistingpage.domain.model.ArtistListingDataData
import com.example.deezerapp.common.adapter.BaseRecyclerViewAdapter
import com.example.deezerapp.common.util.createPlaceHolder
import com.example.deezerapp.common.util.inflate
import com.example.deezerapp.common.util.setImage
import com.example.deezerapp.databinding.AlbumItemDesignBinding
import javax.inject.Inject

class AlbumListingAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<AlbumListingDataData,
            AlbumItemDesignBinding>() {
    var onAlbumClick: ((trending: AlbumItemDesignBinding) -> Unit)? = null

    inner class TrendingItemViewHolder(private val binding: AlbumItemDesignBinding) :
        ViewBindingViewHolder(binding) {
        override fun bind(item: AlbumListingDataData) {
            with(binding) {
                cardViewAlbum.setOnClickListener {
                    onAlbumClick?.invoke(binding)
                }
                imageViewAlbumImage.setImage(
                    url = item.coverMedium,
                    placeholder = createPlaceHolder(root.context)
                )
                textViewReleaseDate.text = item.releaseDate
                textViewAlbumName.text = item.title
            }
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return TrendingItemViewHolder(parent.inflate(AlbumItemDesignBinding::inflate))
    }
}