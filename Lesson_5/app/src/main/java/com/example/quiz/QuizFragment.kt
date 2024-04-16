package com.example.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            findNavController().popBackStack()
        }

        binding.sendBtn.setOnClickListener {
            val count = score().toString()
            val bundle = Bundle()
            bundle.putString("totalScore", count)
            findNavController().navigate(R.id.action_QuizFragment_to_ResultFragment, bundle)
        }
        return binding.root
    }

    private fun score(): Int {
        var count: Int = 0
        if(binding.firstQuestionGroup.checkedRadioButtonId == binding.firstQuestionOption2.id) count++
        if(binding.secondQuestionGroup.checkedRadioButtonId == binding.secondQuestionOption3.id) count++
        if(binding.thirdQuestionGroup.checkedRadioButtonId == binding.thirdQuestionOption1.id) count++
        return count
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}