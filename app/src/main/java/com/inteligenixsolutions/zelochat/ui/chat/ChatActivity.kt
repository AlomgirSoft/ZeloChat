package com.inteligenixsolutions.zelochat.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.data.chat.Chat
import com.inteligenixsolutions.zelochat.data.registretion.RequestUserRegister
import com.inteligenixsolutions.zelochat.databinding.ActivityChatBinding
import com.inteligenixsolutions.zelochat.ui.fragment.profile.OthersProfileActivity
import com.inteligenixsolutions.zelochat.utils.updateOnlineStatus
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject
@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    companion object {
        const val REMOTE_USER_KEY = "remote_user_key_id"

    }

    val viewModel: ChatViewModel by viewModels()
   // private lateinit var message:String

    @Inject
    lateinit var user: FirebaseUser

    private var remoteUserID: String = ""
    lateinit var adapter: ChatAdapter

    lateinit var binding:ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.getStringExtra(REMOTE_USER_KEY)?.let {
            remoteUserID =it
            viewModel.getChat(myId = user.uid, remoteUserID = remoteUserID)
          Log.d("TAG","remoteId{${ChatActivity.javaClass.name}$it")
        }

        binding.sendBtn.setOnClickListener {


              val  message = binding.textInputEdt.text.toString().trim()


            val chatID = UUID.randomUUID().toString()
            if (message.isNotEmpty()){
                val chat = Chat(
                    senderID = user.uid,
                    receiverID = remoteUserID,
                    message = message,
                    chatID = chatID,
                    System.currentTimeMillis()
                )

                viewModel.sendMessage(chat)
            }




            binding.textInputEdt.text?.clear()


        }




        viewModel.responseMessageSend.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }
        viewModel.responseChat.observe(this) { list ->
            val sortedChat = list.sortedBy { it.timeStamp }


            adapter = ChatAdapter(sortedChat, myID = user.uid)

            val lManger = LinearLayoutManager(this)
            lManger.stackFromEnd = true
            binding.chatRCV.layoutManager = lManger
            binding.chatRCV.adapter = adapter


        }


    }



    override fun onResume() {
        super.onResume()
        updateOnlineStatus("online")
    }

    override fun onPause() {
        super.onPause()
        updateOnlineStatus("offline")
    }
}