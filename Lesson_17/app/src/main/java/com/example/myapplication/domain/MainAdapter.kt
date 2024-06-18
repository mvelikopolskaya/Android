package com.example.myapplication.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.Photo
import com.example.myapplication.data.PhotoDto
import com.example.myapplication.databinding.PhotoBinding



class MainAdapter(
    private val onClick:(Photo) -> Unit
): RecyclerView.Adapter<MainViewHolder>() {
    private var photosList: PhotoDto? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = PhotoBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photosList?.photos?.size ?: 0
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = photosList?.photos?.get(position)
        if (item != null) {
            with(holder.binding) {
                rover.text = "Rover: ${item.rover.name}"
                camera.text = "Camera: ${item.camera.name}"
                sol.text = "Sol: ${item.sol}"
                earthDate.text = "Earth date: ${item.earthDate}"
                item.let {
                    Glide.with(photoImg.context)
                        .load(item.imgSrc)
                        .into(photoImg)
                    }
                photoImg.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    fun setData(photosList: PhotoDto?){
        this.photosList = photosList
        notifyDataSetChanged()
    }
}

