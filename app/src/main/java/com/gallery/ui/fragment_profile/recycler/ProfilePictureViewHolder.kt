package com.gallery.ui.fragment_profile.recycler

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.gallery.R
import com.gallery.databinding.ProfilePictureViewBinding

class ProfilePictureViewHolder(private val binding: ProfilePictureViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(items: Int) = with(binding) {
        picture.load(items) {

            placeholder(R.drawable.ic_camera)
            crossfade(true)
            crossfade(300)
            transformations(RoundedCornersTransformation(10f))
        }
    }

}