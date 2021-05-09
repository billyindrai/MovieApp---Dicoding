package com.billyindrai.architecturecomponent.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.billyindrai.architecturecomponent.DetailActivity
import com.billyindrai.architecturecomponent.R
import com.billyindrai.architecturecomponent.ViewModel
import com.billyindrai.architecturecomponent.adapter.MovieAdapter
import com.billyindrai.architecturecomponent.adapter.TvShowsAdapter
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows
import com.billyindrai.architecturecomponent.databinding.FragmentTabBinding

class TabFragment : Fragment() {
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(index: Int) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    private lateinit var binding : FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTabBinding.bind(view)
        binding.rvTab.layoutManager = LinearLayoutManager(this.context)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        val viewModel: ViewModel = ViewModelProvider(this@TabFragment).get(ViewModel::class.java)

        when (index) {
            1 -> {
                val adapter = MovieAdapter()
                adapter.notifyDataSetChanged()
                binding.rvTab.adapter = adapter
                binding.pb.visibility =View.VISIBLE

                viewModel.getMovies().observe(viewLifecycleOwner, { listMovie ->
                    if (listMovie != null) {
                        adapter.setMovies(listMovie)
                        binding.pb.visibility = View.GONE
                    }
                })

                adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {

                    override fun onItemClicked(data: Movie) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT,DetailActivity.EXTRA_MOVIE)
                        startActivity(intent)
                    }

                })
            }

            2 -> {
                val adapterTv = TvShowsAdapter()
                adapterTv.notifyDataSetChanged()
                binding.rvTab.adapter = adapterTv
                binding.pb.visibility =View.VISIBLE

                viewModel.getTVShow().observe(viewLifecycleOwner, { listTv ->
                    if (listTv != null) {
                        adapterTv.setTvShows(listTv)
                        binding.pb.visibility = View.GONE
                    }
                })

                adapterTv.setOnItemClickCallback(object : TvShowsAdapter.OnItemClickCallback {

                    override fun onItemClicked(data: TvShows) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT,DetailActivity.EXTRA_TV)
                        startActivity(intent)
                    }
                })
            }
        }
    }
}