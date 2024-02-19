package com.gallery.ui.new_fragment.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gallery.R
import com.gallery.databinding.PictureViewBinding

class PictureViewHolder(private val binding: PictureViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(items: Int) = with(binding) {
        picture.load(items) {

            placeholder(R.drawable.ic_camera)
            crossfade(true)
            crossfade(300)
            transformations(RoundedCornersTransformation(20f))
        }
    }

}