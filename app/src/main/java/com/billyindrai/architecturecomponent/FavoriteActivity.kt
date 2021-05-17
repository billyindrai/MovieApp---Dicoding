package com.billyindrai.architecturecomponent

import android.os.Bundle
import android.view.Menu
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.billyindrai.architecturecomponent.adapter.FavoritePagerAdapter
import com.billyindrai.architecturecomponent.adapter.PagerAdapter
import com.billyindrai.architecturecomponent.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tv_shows
        )
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f


        val pager = FavoritePagerAdapter(this)
        binding.viewPagerFav.adapter = pager

        TabLayoutMediator(binding.tabLayoutFav, binding.viewPagerFav) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}