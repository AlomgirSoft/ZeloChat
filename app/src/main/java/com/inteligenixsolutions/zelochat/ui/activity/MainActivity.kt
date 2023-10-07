package com.inteligenixsolutions.zelochat.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.utils.updateOnlineStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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