package com.gallery.ui.new_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.gallery.R
import com.gallery.databinding.FragmentNewBinding
import com.gallery.ui.new_fragment.mockup.MockupPictures
import com.gallery.ui.new_fragment.recycler.PictureAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class NewFragment : Fragment() {
    private lateinit var binding: FragmentNewBinding

    private val adapter = PictureAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        binding.loadingGroup.isVisible = true
        lifecycleScope.launch {
            delay(2000)
            adapter.add(MockupPictures.get())
            withContext(Dispatchers.Main){
                binding.loadingGroup.isVisible = false
                adapter.notifyDataSetChanged()
            }

        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewFragment()
    }
}