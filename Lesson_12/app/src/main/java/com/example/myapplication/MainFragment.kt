package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    private val mainViewModel : MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)

        val searchBtn = binding.searchBtn

        binding.searchRequest.addTextChangedListener {
            if(binding.searchRequest.text.toString().length < 3) {
                searchBtn.isEnabled = false
            } else {
                searchBtn.isEnabled = true
            }
        }

        searchBtn.setOnClickListener {
            val searchRequest = binding.searchRequest.text.toString()
            mainViewModel.onButtonClick(searchRequest, this.requireContext())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.state.collect { state ->
                    when(state){
                        State.Loading -> {
                            binding.searchProgress.isIndeterminate = true
                            binding.searchProgress.visibility = View.VISIBLE
                            binding.searchResult.text = null
                        }
                        State.Complete -> {
                            binding.searchProgress.isIndeterminate = false
                            binding.searchProgress.visibility = View.GONE
                            binding.searchResult.text = "${resources.getString(R.string.search_result)} ${binding.searchRequest.text.toString()}"
                        }
                    }
                }
            }
        }
        return binding.root
    }
}