package com.example.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import quiz.R
import quiz.databinding.FragmentWelcomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar


class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd.MM.yy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater)

        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_WelcomeFragment_to_QuizFragment)
        }

        binding.birthdatePickerBtn.setOnClickListener {
            val calendarConstraint = CalendarConstraints.Builder().setOpenAt(calendar.timeInMillis).build()
            val dateDialogue = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(calendarConstraint)
                .setTitleText(R.string.date_picker_title).build()
            dateDialogue.addOnPositiveButtonClickListener{ timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(binding.birthdatePickerBtn, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
            }
            dateDialogue.show(getParentFragmentManager(), "DatePicker")
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}