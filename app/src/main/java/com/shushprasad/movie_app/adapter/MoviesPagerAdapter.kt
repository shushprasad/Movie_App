package com.shushprasad.movie_app.adapter

import androidx.fragment.app.Fragment
import com.shushprasad.movie_app.fragments.LatestMoviesFragment
import com.shushprasad.movie_app.fragments.PopularMoviesFragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoviePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LatestMoviesFragment()
            else -> PopularMoviesFragment()
        }
    }
}
