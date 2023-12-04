package com.inteligenixsolutions.zelochat.ui.fragment.home


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.inteligenixsolutions.zelochat.R

import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.databinding.FragmentHomeBinding
import com.inteligenixsolutions.zelochat.ui.fragment.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate){



    private val viewModel: ProfileViewModel by viewModels()
   lateinit var user: FirebaseUser



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.user = FirebaseAuth.getInstance().currentUser!!
            user.let {
                viewModel.getUserById(it.uid)
            }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar.myToolbar)

        val pagerAdapter = FPagerAdapter(parentFragmentManager)
        binding.fragmentViewpager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.fragmentViewpager)


        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.round_home_24)
        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.chats_icon)
       // binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.baseline_search_24)
        binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.baseline_notifications_24)
        binding.tabLayout.getTabAt(3)?.setIcon(R.drawable.baseline_co_present_24)

    }



}