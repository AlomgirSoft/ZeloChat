package com.inteligenixsolutions.zelochat.ui.fragment.profile


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
//  private val viewModel:ProfileViewModel by viewModels()
//
//lateinit var user:FirebaseUser
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//       user = FirebaseAuth.getInstance().currentUser!!
//        user?.let {
//            viewModel.getUserById(it.uid)
//        }
//
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//  val  auth= FirebaseAuth.getInstance()
//        binding.logoutBtn.setOnClickListener {
//
//            auth.signOut()
//            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
//        }
//        viewModel.responseUserProfile.observe(viewLifecycleOwner) {
//
//            binding.apply {
//                userName.text=(it.name)
//                userEmail.text=(it.email)
//                userAbout.text=(it.about)
//                profileImage.load(it.image)
//                coverImage.load(it.image)
//
//
//            }
//
//
//
//        }
//
//
//
//
//
//    }
}