package com.example.deezerapp.albumdetailpage.ui.adapter


import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.deezerapp.albumdetailpage.domain.model.TrackDomainDataData
import com.example.deezerapp.common.adapter.BaseRecyclerViewAdapter
import com.example.deezerapp.common.util.createPlaceHolder
import com.example.deezerapp.common.util.inflate
import com.example.deezerapp.common.util.setImage
import com.example.deezerapp.databinding.MusicItemDesignBinding
import javax.inject.Inject
import com.example.deezerapp.R
import com.example.deezerapp.albumdetailpage.data.source.local.db.MusicDatabase


class MusicAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<TrackDomainDataData,
            MusicItemDesignBinding>() {
    var onMusicClick: ((music: TrackDomainDataData) -> Unit)? = null
    var onMusicSaveClick: ((music: TrackDomainDataData, isSaved: Boolean,pos:Int) -> Unit)? = null

    val list = mutableListOf<Long>()

    inner class MusicItemViewHolder(private val binding: MusicItemDesignBinding) :
        ViewBindingViewHolder(binding) {

        fun setSavedBg(context: Context) {
            binding.imageViewSave.background =
                ContextCompat.getDrawable(context, R.drawable.ic_saved)
        }

        fun setUnSavedBg(context: Context) {
            binding.imageViewSave.background =
                ContextCompat.getDrawable(context, R.drawable.ic_save)
        }

        fun isSaved(item: TrackDomainDataData): Boolean {
            if (list.isNotEmpty()) {
                return list.contains(item.trackId)
            }
            return false
        }

        override fun bind(item: TrackDomainDataData) {
            with(binding) {
                imageViewAlbumImage.setImage(
                    item.album?.coverMedium,
                    createPlaceHolder(root.context)
                )
                if (list.isNotEmpty()) {
                    if (list.contains(item.trackId)) {
                        imageViewSave.setImageResource(R.drawable.ic_saved)
                    } else {
                        imageViewSave.setImageResource(R.drawable.ic_save)
                    }
                }
                textViewMusicName.text = item.title
                textViewDuration.text = "${item.duration.toString()}\""
                cardViewMusic.setOnClickListener {
                    onMusicClick?.invoke(item)
                }
                imageViewSave.setOnClickListener {
                    onMusicSaveClick?.invoke(item, isSaved(item),adapterPosition)
                }
            }
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return MusicItemViewHolder(parent.inflate(MusicItemDesignBinding::inflate))
    }

    fun getIdList(idList: MutableList<Long>) {
        list.clear()
        list.addAll(idList)
    }
}