package com.gallery.ui.fragment_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gallery.R
import com.gallery.databinding.FragmentMainBinding
import com.gallery.ui.fragment_main.pager.MainPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = MainPagerAdapter(childFragmentManager, lifecycle)
        binding.tabLayout.tabRippleColor = null
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.new_)
                1 -> tab.text = getString(R.string.popular)
            }
        }.attach()
    }
}