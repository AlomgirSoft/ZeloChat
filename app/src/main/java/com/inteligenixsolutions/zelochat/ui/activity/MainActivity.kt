package com.inteligenixsolutions.zelochat.ui.activity


import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


import com.inteligenixsolutions.zelochat.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onBackPressed(){
        super.onBackPressed()
        
    }


//    override fun onResume() {
//        super.onResume()
//        updateOnlineStatus("online")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        updateOnlineStatus("offline")
//    }
}