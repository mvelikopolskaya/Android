package com.example.quiz

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import quiz.R
import quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private var param1: String? = null
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater)

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_QuizFragment_to_WelcomeFragment)
        }

        binding.sendBtn.setOnClickListener {
            val count = score().toString()
            val bundle = Bundle()
            bundle.putString("totalScore", count)
            findNavController().navigate(R.id.action_QuizFragment_to_ResultFragment, bundle)
        }

        val animationSetAlpha = AnimatorSet()
        val question_1_group = setAlpha(binding.questionGroup1)
        val question_2_group = setAlpha(binding.questionGroup2)
        val question_3_group = setAlpha(binding.questionGroup3)
        animationSetAlpha.playTogether(question_1_group, question_2_group, question_3_group)
        animationSetAlpha.apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
        return binding.root
    }

    private fun setAlpha(group : RadioGroup) : ObjectAnimator{
        return ObjectAnimator.ofFloat(group, View.ALPHA, 0f, 1f)
    }

    private fun score(): Int {
        var count: Int = 0
        if(binding.questionGroup1.checkedRadioButtonId == binding.question1Option2.id) count++
        if(binding.questionGroup2.checkedRadioButtonId == binding.question2Option3.id) count++
        if(binding.questionGroup3.checkedRadioButtonId == binding.question3Option1.id) count++
        return count
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}