package com.example.quiz

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import quiz.R
import quiz.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater)

        binding.retakeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_ResultFragment_to_QuizFragment)
        }

        val animationSetScale = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(binding.resultCount, View.SCALE_X, 2f)
        val scaleY = ObjectAnimator.ofFloat(binding.resultCount, View.SCALE_Y, 2f)
        animationSetScale.playTogether(scaleX, scaleY)
        animationSetScale.apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        ObjectAnimator.ofArgb(binding.retakeBtn, "textColor",
            Color.parseColor("#FFFFFFFF"),
            Color.parseColor("#E91E3C")).apply {
                duration = 3000
            start()
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = arguments?.getString("totalScore")
        binding.resultCount.text = resources.getString(R.string.result_count) + " " + score
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}