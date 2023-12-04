package com.inteligenixsolutions.zelochat.ui.fragment.follow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inteligenixsolutions.zelochat.data.registretion.User
import com.inteligenixsolutions.zelochat.databinding.ItemFollowerBinding

class FollowAdapter (val  list: List<User>, val  context: Context): RecyclerView.Adapter<FollowAdapter.CountViewHolder>() {





    class CountViewHolder (var binding:ItemFollowerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountViewHolder {
        return CountViewHolder(ItemFollowerBinding.inflate(LayoutInflater.from(context),parent,false))

    }

    override fun getItemCount(): Int {
       return list.size

    }

    override fun onBindViewHolder(holder: CountViewHolder, position: Int) {
         list[position].let {
             holder.binding.nameTv.text = it.name

             holder.binding.profileImage.load(it.image)
         }
    }
}