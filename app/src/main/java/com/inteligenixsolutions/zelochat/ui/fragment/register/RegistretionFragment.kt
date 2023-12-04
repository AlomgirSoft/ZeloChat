package com.inteligenixsolutions.zelochat.ui.fragment.register


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.data.registretion.User
import com.inteligenixsolutions.zelochat.databinding.FragmentRegistretionBinding
import com.inteligenixsolutions.zelochat.ui.fragment.login.LoginFragment
import com.inteligenixsolutions.zelochat.utils.DummyImgLink
import com.inteligenixsolutions.zelochat.utils.ERROR
import com.inteligenixsolutions.zelochat.utils.SUCCESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RegistretionFragment : BaseFragment<FragmentRegistretionBinding>(FragmentRegistretionBinding::inflate) {



 val viewModel:RegisterViewModel by viewModels ()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.responseRegistration.observe(viewLifecycleOwner) {
            when (it) {
                SUCCESS -> {
                    Toast.makeText(requireContext(), SUCCESS, Toast.LENGTH_LONG)
                        .show()
                   navigateTo(LoginFragment())

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

                val request = User(

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




    private fun navigateTo(fragment: Fragment) {
        val transaction =  (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)

        transaction.commit()
    }

}