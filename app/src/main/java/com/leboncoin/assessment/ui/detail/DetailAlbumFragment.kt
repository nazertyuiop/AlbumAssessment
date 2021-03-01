package com.leboncoin.assessment.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.leboncoin.assessment.R
import com.leboncoin.assessment.databinding.FragmentDetailAlbumBinding
import com.leboncoin.core.network.isNetworkRelated
import com.leboncoin.domain.model.Album
import com.leboncoin.viewutils.setImageUrl
import org.koin.android.viewmodel.ext.android.viewModel



class DetailAlbumFragment : Fragment(R.layout.fragment_detail_album) {

    private var _binding: FragmentDetailAlbumBinding? = null
    private val binding: FragmentDetailAlbumBinding get() = requireNotNull(_binding)

    private val viewModel by viewModel<AlbumDetailViewModel>()
    private var idAlbum: Int = -1

    companion object {
        const val ID_ALBUM = "ID_ALBUM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ID_ALBUM)?.let {
            idAlbum = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailAlbumBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.getDetailAlbum(idAlbum)
    }


    private fun observeViewModel() {
        viewModel.getAlbumEvent.observe(viewLifecycleOwner, Observer { albumEvent ->
            when (albumEvent) {
                is AlbumDetailViewModel.AlbumEvent.Loading -> toggleLoading(true)
                is AlbumDetailViewModel.AlbumEvent.Error -> handleAlbumEventError(albumEvent.exception)
                is AlbumDetailViewModel.AlbumEvent.Success -> handleAlbumEventSuccess(albumEvent.album)
            }
        })
    }

    private fun handleAlbumEventSuccess(album: Album) {
        toggleLoading(false)
        binding.albumImage.setImageUrl(album.imgUrl)
        binding.albumTitle.text = album.title

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}