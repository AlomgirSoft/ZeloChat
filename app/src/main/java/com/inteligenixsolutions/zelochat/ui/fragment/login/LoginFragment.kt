package com.inteligenixsolutions.zelochat.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.data.login.RequestUserLogin
import com.inteligenixsolutions.zelochat.databinding.FragmentLoginBinding
import com.inteligenixsolutions.zelochat.utils.ERROR
import com.inteligenixsolutions.zelochat.utils.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

  private val viewModel:LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.responseLogin.observe(viewLifecycleOwner) {
            when (it) {
                SUCCESS -> {
                    Toast.makeText(requireContext(), SUCCESS, Toast.LENGTH_LONG)
                        .show()
                   findNavController().navigate(R.id.action_loginFragment_to_homeFragment)


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




        binding.loginBtn.setOnClickListener {
            val email = binding.edEmail.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()

            login(email, password)


        }


        binding.rejistretionBtn.setOnClickListener {


            findNavController().navigate(R.id.action_loginFragment_to_registretionFragment)


        }
    }

    private fun login(email: String, password: String) {
        val request = RequestUserLogin(email = email, password = password)


        viewModel.login(request)


    }

}