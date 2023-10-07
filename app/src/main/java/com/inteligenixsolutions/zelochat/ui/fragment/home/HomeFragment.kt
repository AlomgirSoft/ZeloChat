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

    private lateinit var toggle: ActionBarDrawerToggle

    private val viewModel: ProfileViewModel by viewModels()

  private var uri:Uri?=null
    private lateinit var    imag:ImageView
    private lateinit var saveIcon:ImageView

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
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar.myToolbar)

        val pagerAdapter = FPagerAdapter(parentFragmentManager)
        binding.fragmentViewpager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.fragmentViewpager)


        toggle = ActionBarDrawerToggle(
            requireActivity(),
            binding.root,
            binding.toolbar.myToolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.root.addDrawerListener(toggle)
        toggle.syncState()

        val headerView = binding.navigationView.getHeaderView(0)



        var name = headerView.findViewById<TextView>(R.id.profileNameTV)
        var email = headerView.findViewById<TextView>(R.id.profileUserEmailTv)
         saveIcon = headerView.findViewById<ImageView>(R.id.saveIconBtn)
        imag = headerView.findViewById<ImageView>(R.id.profile_image)
        
        
        imag.setOnClickListener {

            ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(512, 512)  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForResult.launch(intent)


                }
   saveIcon.visibility =View.VISIBLE

        }



        viewModel.responseUserProfile.observe(viewLifecycleOwner) {
             name.text=it.name
             email.text=it.email
             imag.load(it.image)
          //   image.load(it.image)

          binding.toolbar.myToolbar.apply {

          }



        }



        saveIcon.setOnClickListener {_->

            if (uri != null) {
                viewModel.uploadImage(uri!!, requireContext())
               // saveIcon.visibility =View.GONE
            } else {
                // Handle the case where no image is selected
                Toast.makeText(requireContext(), "Please select an image", Toast.LENGTH_SHORT).show()
            }





        }
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home-> {
                    // Handle item 1 click
                    Toast.makeText(requireContext(),"done", Toast.LENGTH_LONG).show()
                }
                R.id.logout-> {
                    val auth= FirebaseAuth.getInstance()
                    auth.signOut()

                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)

                }
                // Add more cases for other menu items if needed
            }
            // Close the drawer when an item is clicked
            binding.root.closeDrawers()
            true
        }

    }
//
//    private val startForProfileImageResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//
//            when (resultCode) {
//                Activity.RESULT_OK -> {
//
//                    uri = data?.data!!
//
//               imag.load(uri)
//                    saveIcon.visibility=View.VISIBLE
//                }
//
//
//
//                ImagePicker.RESULT_ERROR -> {
//                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                else -> {
//                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }



    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                uri = result.data?.data
                if (uri != null) {
                    imag.setImageURI(uri)

                }
            }
        }

}