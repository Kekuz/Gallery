package com.gallery.ui.fragment_profile.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gallery.databinding.ProfilePictureViewBinding

class ProfilePictureAdapter : ListAdapter<Int, ProfilePictureViewHolder>(ProfilePictureComparator()) {

    private val pictures = mutableListOf<Int>()

    fun clear() = pictures.clear()

    fun add(pictures: List<Int>) =
        this.pictures.addAll(pictures)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePictureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProfilePictureViewBinding.inflate(inflater, parent, false)
        return ProfilePictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilePictureViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    override fun getItemCount(): Int {
        return pictures.size
    }
}