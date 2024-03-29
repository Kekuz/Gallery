package com.gallery.ui.fragment_main.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gallery.ui.new_fragment.NewFragment
import com.gallery.ui.popular_fragment.PopularFragment

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            NewFragment.newInstance()
        } else {
            PopularFragment.newInstance()
        }
    }
}