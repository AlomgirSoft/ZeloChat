package com.inteligenixsolutions.zelochat.ui.fragment.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser

import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.data.user.UserProfile
import com.inteligenixsolutions.zelochat.databinding.FragmentUserBinding
import com.inteligenixsolutions.zelochat.ui.chat.ChatActivity
import com.inteligenixsolutions.zelochat.ui.fragment.profile.OthersProfileActivity
import com.inteligenixsolutions.zelochat.ui.fragment.profile.ProfileViewModel
import com.inteligenixsolutions.zelochat.utils.updateOnlineStatus
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate),UserAdapter.Listener {
    private val viewModel: ProfileViewModel by viewModels()
    @Inject
    lateinit var  user:FirebaseUser

    val userList= mutableListOf<UserProfile>()

    private lateinit var adapter: UserAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter(this,true)

        viewModel.getAllUser()



        viewModel.responseAllUserProfile.observe(viewLifecycleOwner) {
            userList.clear()
            it.forEach {fUser->
                if (fUser.userId != user.uid){
                    userList.add(fUser)
                }

            }

            adapter.submitList(userList)
            binding.userRcv.adapter = adapter

            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        val filteredList = ArrayList<UserProfile>()
                        for (i in it) {
                            if (i.name.lowercase(Locale.ROOT).contains(newText)) {
                                filteredList.add(i)
                            }
                        }

                        if (filteredList.isEmpty()) {
                            Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
                        } else {
                            adapter.submitList(filteredList)
                            adapter.notifyDataSetChanged()

                        }
                    }
                    return true
                }


            })
        }



    }

    override fun profileClicked(userId: String) {
        val intent=Intent(requireContext(),OthersProfileActivity::class.java)
        intent.putExtra(OthersProfileActivity.USER_KEY,userId)
        startActivity(intent)
    }

    override fun messageMeClicked(userId: String) {
        val intent=Intent(requireContext(),ChatActivity::class.java)
        intent.putExtra(ChatActivity.REMOTE_USER_KEY,userId)
        startActivity(intent)
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