package com.example.myapplication.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.Photo
import com.example.myapplication.domain.MainAdapter
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.di.DaggerAppComponent
import com.example.myapplication.domain.State
import kotlinx.coroutines.launch


const val PHOTO = "photo"

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels{
        DaggerAppComponent.create().mainViewModelFactory()
    }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    private val adapter = MainAdapter{photo ->  onItemClick(photo)}

    companion object {
        fun newInstance() = MainFragment()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loadPhotos()
                viewModel.photos.collect{
                    adapter.setData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{state ->
                    when(state){
                        State.Loading -> {
                            binding.swipeRefreshLayout.isRefreshing = true
                        }
                        State.Complete -> {
                            binding.swipeRefreshLayout.isRefreshing = false
                        }                        }
                    }
                }
            }
        return binding.root
    }

    private fun onItemClick(photo: Photo) {
        val bundle = Bundle()
        bundle.putString(PHOTO, photo.imgSrc)
        findNavController().navigate(R.id.action_MainFragment_to_PhotoFragment, bundle)
    }
}