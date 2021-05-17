package com.billyindrai.architecturecomponent

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.billyindrai.architecturecomponent.adapter.PagerAdapter
import com.billyindrai.architecturecomponent.databinding.ActivityMainBinding
import com.billyindrai.architecturecomponent.databinding.FragmentTabBinding
import com.billyindrai.architecturecomponent.viewmodel.ViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tv_shows
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        val pager = PagerAdapter(this)
        binding.viewPager.adapter = pager

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = findViewById<SearchView>(R.id.sv_search)
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.queryHint = resources.getString(R.string.search)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
////                showLoading(true)
//                if(TAB_TITLES[0].toString() == "Movies" ) {
//                    viewModel.searchMovie(query!!)
//                }else{
//                    viewModel.searchTv(query!!)
//
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//
//        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}