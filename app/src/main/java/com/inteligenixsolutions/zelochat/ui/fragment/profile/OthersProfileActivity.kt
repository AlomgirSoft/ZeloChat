package com.inteligenixsolutions.zelochat.ui.fragment.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.databinding.ActivityOthersProfileBinding
import com.inteligenixsolutions.zelochat.ui.chat.ChatActivity
import com.inteligenixsolutions.zelochat.utils.UserOfflineOnlineStatus
import com.inteligenixsolutions.zelochat.utils.updateOnlineStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OthersProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityOthersProfileBinding
    private val viewModel:ProfileViewModel by viewModels()
    private lateinit var user:FirebaseUser

    companion object {
        const val USER_KEY = "user_key_id"
    }
    var remoteUserId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityOthersProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra(USER_KEY)?.let {
            viewModel.getUserById(it)
            remoteUserId =it
        }


        viewModel.responseUserProfile.observe(this) {

            binding.apply {
                userName.text=(it.name)
                userEmail.text=(it.email)
                userAbout.text=(it.about)
                profileImage.load(it.image)
                coverImage.load(it.image)


            }



        }

        binding.chatBtn.setOnClickListener {_->
            remoteUserId.let {
                val intent =Intent(this@OthersProfileActivity,ChatActivity::class.java)
                intent.putExtra(ChatActivity.REMOTE_USER_KEY,it)
                startActivity(intent)
            }



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