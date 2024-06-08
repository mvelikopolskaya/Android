package com.example.myapplication.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.di.DaggerAppComponent
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels{
      DaggerAppComponent.create().mainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.refreshBtn.setOnClickListener {
            viewModel.reloadPost()
            lifecycleScope.launch {
                viewModel.post.collect{post ->
                    binding.textView.text = post
                }
            }
        }
        return binding.root
    }
}