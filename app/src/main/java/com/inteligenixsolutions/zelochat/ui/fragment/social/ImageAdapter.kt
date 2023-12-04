package com.inteligenixsolutions.zelochat.ui.fragment.social

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.data.image.Image
import com.inteligenixsolutions.zelochat.databinding.ImageLayoutBinding
import com.squareup.picasso.Picasso

class ImageAdapter(private val images: List<Uri>) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ImageLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
     return   images.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = images[position]

        Log.d("TAG",currentItem.toString())

        holder.binding.image.setImageURI(currentItem)

      //  holder.binding.image.load(currentItem)


    }

}