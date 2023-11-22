package com.chetan.moengageassignment.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chetan.moengageassignment.R
import com.chetan.moengageassignment.databinding.FragmentNewsBinding
import com.chetan.moengageassignment.models.NewsListResponse
import com.chetan.moengageassignment.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections


@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel by viewModels<NewsViewModel>()
    private var newsList : List<NewsListResponse.Article>? =null

    private lateinit var adapter: NewsAdapter

    private var sortedOldToNew = false;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        adapter = NewsAdapter(::onArticalClicked)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.getAllNews()
        binding.newsList.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.newsList.adapter = adapter

        bindObservers()

        binding.sort.setOnClickListener {
            if(sortedOldToNew){
                // Sorting in ascending order (old to new)
                val sortedList = newsList?.sortedBy { it.publishedAt }
                newsList = sortedList
                adapter.submitList(newsList)
                sortedOldToNew=false

                binding?.sort?.setImageResource(R.drawable.ic_sort_asc)
            }else{
                val reversedList = newsList?.reversed()
                newsList = reversedList
                adapter.submitList(newsList)
                sortedOldToNew=true
                binding?.sort?.setImageResource(R.drawable.ic_sort_desc)
            }

        }
    }

    private fun bindObservers() {
        newsViewModel.newsListLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    newsList= (it.data?.articles as ArrayList<NewsListResponse.Article>?)!!
                    adapter.submitList(newsList)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    private fun onArticalClicked(article: NewsListResponse.Article){
        Log.d("onArticalClicked: ","NewsListResponse::"+article)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}