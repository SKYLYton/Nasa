package com.nasa.ui.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasa.databinding.ItemImageBinding
import com.nasa.model.ImageModel

class ImagesAdapter(private val context: Context) : RecyclerView.Adapter<PagerVH>() {

    var isHd = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var images = emptyList<ImageModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.binding.apply {
            if (isHd) {
                Glide.with(context).load(images[position].hdurl).into(image)
            } else {
                Glide.with(context).load(images[position].url).into(image)
            }
        }
    }
}

class PagerVH(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)