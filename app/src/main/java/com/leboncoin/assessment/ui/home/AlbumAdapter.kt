package com.leboncoin.assessment.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.assessment.R
import com.leboncoin.assessment.databinding.AlbumListItemBinding
import com.leboncoin.domain.model.Album
import com.leboncoin.viewutils.inflate
import com.leboncoin.viewutils.setImageUrl


class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    var items = listOf<Album>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var itemClickListener: ((Album) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(parent.inflate(R.layout.album_list_item))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item: Album = items[position]
        holder.bind(item)
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AlbumListItemBinding.bind(itemView)

        fun bind(item: Album) = with(item) {
            binding.albumImage.setImageUrl(thumbnailUrl)
            binding.albumName.text = title
            itemView.setOnClickListener { itemClickListener?.invoke(item) }
        }
    }
}