package com.inteligenixsolutions.zelochat.ui.fragment.social

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.inteligenixsolutions.zelochat.databinding.ImageLayoutBinding

class ImageListingAdapter(
    val onCancelClicked: ((Int, Uri) -> Unit)? = null,
) : RecyclerView.Adapter<ImageListingAdapter.MyViewHolder>() {

    private var list: MutableList<Uri> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ImageLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item,position)
    }

    fun updateList(list: MutableList<Uri>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Uri,position: Int) {
           // binding.image.setImageURI(item)
            binding.image.load(item)


            binding.cancel.setOnClickListener {
//                onCancelClicked?.invoke(absoluteAdapterPosition, item)
//                adapter.remove(adapter.getItem(position));
                list.removeAt(position);
            }
        }
    }
}