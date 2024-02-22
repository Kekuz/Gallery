package com.gallery.ui.fragment_profile.recycler

import androidx.recyclerview.widget.DiffUtil

class ProfilePictureComparator: DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(
        oldItem: Int,
        newItem: Int,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Int,
        newItem: Int,
    ): Boolean {
         return oldItem == newItem
    }
}