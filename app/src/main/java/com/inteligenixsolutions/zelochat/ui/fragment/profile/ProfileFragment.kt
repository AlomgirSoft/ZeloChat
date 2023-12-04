package com.inteligenixsolutions.zelochat.ui.fragment.profile


import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager


import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast


import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.inteligenixsolutions.zelochat.R


import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.databinding.FragmentProfileBinding
import com.inteligenixsolutions.zelochat.ui.fragment.login.LoginFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels()

    lateinit var user: FirebaseUser
    private var uri: Uri? = null

    private var allPermissionGranted: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = FirebaseAuth.getInstance().currentUser!!
        user?.let {
            viewModel.getUserById(it.uid)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()

        viewModel.responseUserProfile.observe(viewLifecycleOwner) {

            binding.apply {
                nameTv.text = (it.name)

                aboutTv.text = (it.about)
                profileImage.load(it.image)
                coverImage.load(it.image)


            }


        }
        binding.setting.setOnClickListener {
            auth.signOut()


            navigateTo(LoginFragment())


        }





        binding.profileImage.setOnClickListener {

            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(512, 512)
                .createIntent { intent ->
                    startForResult.launch(intent)
                }

        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                uri = result.data?.data
                if (uri != null) {
                    binding.profileImage.setImageURI(uri)

                    uri?.let {
                        viewModel.uploadImage(it, requireContext())
                    }



                }

            }
        }


    private fun navigateTo(fragment: Fragment) {
        val transaction = (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)

        transaction.commit()
    }










    }