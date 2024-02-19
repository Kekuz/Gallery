package com.gallery.ui.new_fragment.recycler

import androidx.recyclerview.widget.DiffUtil

class PictureComparator: DiffUtil.ItemCallback<Int>() {
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