package com.leboncoin.assessment.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.leboncoin.assessment.R
import com.leboncoin.assessment.databinding.FragmentHomeBinding
import com.leboncoin.core.network.isNetworkRelated
import com.leboncoin.domain.model.Album
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = requireNotNull(_binding)

    private val viewModel by viewModel<HomeViewModel>()
    private val adapter by lazy { AlbumAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        observeViewModel()
        viewModel.getListAlbum()
    }

    private fun initRecycleView() {
        binding.albumRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.albumRecyclerView.adapter = adapter
        adapter.itemClickListener = ::showDetailAlbum
    }

    private fun observeViewModel() {
        viewModel.getListAlbumEvent.observe(viewLifecycleOwner, Observer { albumEvent ->
            when (albumEvent) {
                is HomeViewModel.AlbumEvent.Loading -> toggleLoading(true)
                is HomeViewModel.AlbumEvent.Error -> handleAlbumEventError(albumEvent.exception)
                is HomeViewModel.AlbumEvent.Success -> handleAlbumEventSuccess(albumEvent.items)
            }
        })
    }

    private fun handleAlbumEventSuccess(albums: List<Album>) {
        toggleLoading(false)
        adapter.items = albums
    }

    private fun handleAlbumEventError(e: Exception) {
        toggleLoading(false)
        Toast.makeText(
            requireContext(),
            getString(if (e.isNetworkRelated()) R.string.error_no_connection else R.string.technical_error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun toggleLoading(show: Boolean) {
        binding.loader.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showDetailAlbum(album: Album) {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}