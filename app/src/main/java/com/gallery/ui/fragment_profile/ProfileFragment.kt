package com.gallery.ui.fragment_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gallery.R
import com.gallery.databinding.FragmentProfileBinding
import com.gallery.ui.mockup.MockupPictures
import com.gallery.ui.fragment_profile.recycler.ProfilePictureAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val adapter = ProfilePictureAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        binding.progressBar.isVisible = true
        lifecycleScope.launch {
            delay(2000)
            adapter.add(MockupPictures.get())
            withContext(Dispatchers.Main) {
                binding.progressBar.isVisible = false
                adapter.notifyDataSetChanged()
            }

        }
        binding.iconSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }

    }

}