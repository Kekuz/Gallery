package com.gallery.ui.new_fragment.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gallery.databinding.PictureViewBinding

class PictureAdapter : ListAdapter<Int, PictureViewHolder>(PictureComparator()) {

    private val pictures = mutableListOf<Int>()

    fun clear() = pictures.clear()

    fun add(pictures: List<Int>) =
        this.pictures.addAll(pictures)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PictureViewBinding.inflate(inflater, parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    override fun getItemCount(): Int {
        return pictures.size
    }
}