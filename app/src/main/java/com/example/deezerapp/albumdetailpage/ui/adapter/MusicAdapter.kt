package com.example.deezerapp.albumdetailpage.ui.adapter

import android.view.ViewGroup
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
import com.example.deezerapp.common.adapter.BaseRecyclerViewAdapter
import com.example.deezerapp.common.util.createPlaceHolder
import com.example.deezerapp.common.util.inflate
import com.example.deezerapp.common.util.setImage
import com.example.deezerapp.databinding.MusicItemDesignBinding
import javax.inject.Inject

class MusicAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<TrackDomainDataData,
            MusicItemDesignBinding>() {
    var onMusicClick: ((trending: TrackDomainDataData) -> Unit)? = null

    inner class TrendingItemViewHolder(private val binding: MusicItemDesignBinding) :
        ViewBindingViewHolder(binding) {
        override fun bind(item: TrackDomainDataData) {
            with(binding) {
                imageViewAlbumImage.setImage(item.album?.coverMedium, createPlaceHolder(root.context))
                textViewMusicName.text = item.title
                textViewDuration.text = "${item.duration.toString()}\""
                cardViewMusic.setOnClickListener {
                    onMusicClick?.invoke(item)
                }
            }
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return TrendingItemViewHolder(parent.inflate(MusicItemDesignBinding::inflate))
    }
}