package com.billyindrai.architecturecomponent.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.billyindrai.architecturecomponent.fragment.FavoriteFragment

class FavoritePagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return FavoriteFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return 2
    }
}