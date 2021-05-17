package com.billyindrai.architecturecomponent.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.billyindrai.architecturecomponent.DetailActivity
import com.billyindrai.architecturecomponent.R
import com.billyindrai.architecturecomponent.Sorting
import com.billyindrai.architecturecomponent.adapter.MovieAdapter
import com.billyindrai.architecturecomponent.adapter.MoviePagingAdapter
import com.billyindrai.architecturecomponent.adapter.TvShowsAdapter
import com.billyindrai.architecturecomponent.adapter.TvShowsPagingAdapter
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.FragmentFavoriteBinding
import com.billyindrai.architecturecomponent.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(index: Int) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    private lateinit var binding: FragmentFavoriteBinding
    val favViewModel: FavoriteViewModel by activityViewModels()
    private val movie = MoviePagingAdapter()
    private val tvShow = TvShowsPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoriteBinding.bind(view)
        binding.rvFav.layoutManager = LinearLayoutManager(this.context)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        when (index) {
            1 -> {
                movie.notifyDataSetChanged()
                binding.rvFav.adapter = movie
                binding.pbFav.visibility = View.VISIBLE

                observeData(Sorting.TYPE_MOVIE, Sorting.TITLE)

                movie.setOnItemClickCallback(object : MoviePagingAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Movie) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT, DetailActivity.EXTRA_MOVIE)
                        startActivity(intent)
                    }

                })
            }

            2 -> {
                tvShow.notifyDataSetChanged()
                binding.rvFav.adapter = tvShow
                binding.pbFav.visibility = View.VISIBLE

                observeData(Sorting.TYPE_TVSHOW, Sorting.TITLE)

                tvShow.setOnItemClickCallback(object : TvShowsPagingAdapter.OnItemClickCallback {

                    override fun onItemClicked(data: TvShows) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT, DetailActivity.EXTRA_TV)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_title_asc -> sort = Sorting.TITLE
            R.id.action_top_rating -> sort = Sorting.TOP_RATING
        }
        observeData(Sorting.TYPE_MOVIE, sort)
        observeData(Sorting.TYPE_TVSHOW, sort)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun observeData(type: String, sort: String){
        when (type) {
            Sorting.TYPE_MOVIE -> {
                favViewModel.getAllMovies(sort).observe(viewLifecycleOwner, { listMovies ->
                    if (listMovies != null) {
                        movie.submitList(listMovies)
                        binding.pbFav.visibility = View.GONE
                    }
                })
            }
            Sorting.TYPE_TVSHOW -> {
                favViewModel.getAllTVShows(sort).observe(viewLifecycleOwner, { listTVs ->
                    if (listTVs != null) {
                        tvShow.submitList(listTVs)
                        binding.pbFav.visibility = View.GONE
                    }
                })
            }
        }
    }
}