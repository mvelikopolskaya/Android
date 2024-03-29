package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.myapplication.databinding.ViewCustomBinding
import kotlinx.coroutines.flow.combine


class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,

) : LinearLayout(context, attrs, defStyleAttr) {
    private var binding: ViewCustomBinding
        init {
            binding = ViewCustomBinding.inflate(LayoutInflater.from(context), this, true)
        }

    fun setUpperStringText(text : String){
        binding.upperString.text = text
    }

    fun setLowerStringText(text : String){
        binding.lowerString.text = text
    }
}

