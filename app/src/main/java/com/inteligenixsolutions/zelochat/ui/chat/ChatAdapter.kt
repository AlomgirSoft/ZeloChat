package com.inteligenixsolutions.zelochat.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.data.chat.Chat



class ChatAdapter(private val chatList: List<Chat>, var myID: String) :
    RecyclerView.Adapter<ChatAdapter.VH>() {
    private val RIGHT = 1
    private val LEFT = 2


    class VH(val view: View) : RecyclerView.ViewHolder(view) {
        val messageTv = view.findViewById<TextView>(R.id.messagetxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return if (viewType == RIGHT) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.right_chat_ui, parent, false)

            VH(view)
        } else {

            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.left_chat_ui, parent, false)

            VH(view)
        }


    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val chat = chatList[position]
        holder.messageTv.text = chat.message
    }

    override fun getItemViewType(position: Int): Int {
        return if (chatList[position].senderID == myID) {
            RIGHT
        } else {
            LEFT
        }
    }


}