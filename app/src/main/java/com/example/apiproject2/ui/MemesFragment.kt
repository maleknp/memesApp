package com.example.apiproject2.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
















import com.example.apiproject.ui.AdapterMemes
import com.example.apiproject2.R
import com.example.apiproject2.databinding.FragmentMemesBinding
import com.example.apiproject2.ui.memes_feed.MemesFeedViewModel
import com.example.apiproject2.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemesFragment : Fragment(R.layout.fragment_memes) {

    private var _binding: FragmentMemesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MemesFeedViewModel by viewModels<MemesFeedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMemesBinding.bind(view)

        setupViews()
    }

    fun setupViews() {
        val adapter = AdapterMemes()

        binding.rvMemes.adapter = adapter

        viewModel.memes.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.apply {
                        layoutError.visibility = View.VISIBLE
                        tvError.text = resource.message
                    }
                }
                is Resource.Success -> {
                    binding.apply {
                        layoutError.visibility = View.GONE
                    }
                    adapter.submitList(resource.data)
                }
                is Resource.Loading -> {
                    binding.apply {
                        layoutError.visibility = View.GONE
                    }
                }
            }
        }
    }

}