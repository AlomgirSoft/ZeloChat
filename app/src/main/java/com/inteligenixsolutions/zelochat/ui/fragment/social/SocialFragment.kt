package com.inteligenixsolutions.zelochat.ui.fragment.social

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.data.image.Image
import com.inteligenixsolutions.zelochat.databinding.FragmentSocialBinding
import com.inteligenixsolutions.zelochat.ui.fragment.follow.UserFollowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SocialFragment : BaseFragment<FragmentSocialBinding>(FragmentSocialBinding::inflate) {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videoUpload.setOnClickListener {


        val    bottomSheetDialog = BottomSheetDialog(requireContext())
            val  view =layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            bottomSheetDialog.setContentView(view)
            val image= view.findViewById<TextView>(R.id.uploadImage)

            image.setOnClickListener {
                Toast.makeText(requireContext(), "upload image", Toast.LENGTH_SHORT).show()
                navigateTo(UserFollowFragment())
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.setCancelable(true)
            bottomSheetDialog.create()
            bottomSheetDialog.show()

        }


    }



    private fun navigateTo(fragment: Fragment) {
        val transaction =  (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)

        transaction.commit()
    }




}