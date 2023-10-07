package com.inteligenixsolutions.zelochat.ui.fragment.register


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.data.registretion.RequestUserRegister
import com.inteligenixsolutions.zelochat.databinding.FragmentRegistretionBinding
import com.inteligenixsolutions.zelochat.utils.DummyImgLink
import com.inteligenixsolutions.zelochat.utils.ERROR
import com.inteligenixsolutions.zelochat.utils.SUCCESS
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class RegistretionFragment : BaseFragment<FragmentRegistretionBinding>(FragmentRegistretionBinding::inflate) {



 val viewModel:RegisterViewModel by viewModels ()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.responseRegistration.observe(viewLifecycleOwner) {
            when (it) {
                SUCCESS -> {
                    Toast.makeText(requireContext(), SUCCESS, Toast.LENGTH_LONG)
                        .show()

                }

                ERROR -> {
                    Toast.makeText(requireContext(), ERROR, Toast.LENGTH_LONG)
                        .show()
                }

                else -> {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
                        .show()
                }

            }


        }

        binding.singUpBtn.setOnClickListener {
            binding.apply {
                val name = edName.text.toString()
                val email = edEmail.text.toString()
                val password = edPassword.text.toString()

                val request = RequestUserRegister(

                    name = name,
                    email = email,
                    password = password,
                     createdAt =System.currentTimeMillis(),
                     image = DummyImgLink,


                )
                viewModel.registration(request)

            }


        }


binding.allReadyhaveAccountBtn.setOnClickListener {
  findNavController().popBackStack()
}


        super.onViewCreated(view, savedInstanceState)
    }






}