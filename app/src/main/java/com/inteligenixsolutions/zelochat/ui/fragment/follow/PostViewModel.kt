package com.inteligenixsolutions.zelochat.ui.fragment.follow

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Context
import com.google.firebase.storage.StorageReference
import com.inteligenixsolutions.zelochat.data.post.Post
import com.inteligenixsolutions.zelochat.reposietory.PostRepo
import com.inteligenixsolutions.zelochat.reposietory.UserRepo
import com.inteligenixsolutions.zelochat.utils.ERROR
import com.inteligenixsolutions.zelochat.utils.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(val repo:UserRepo,  private val storageRef: StorageReference, val postRepo:PostRepo):ViewModel() {



    private var _responsePostUpdate = MutableLiveData<String>()
    val responseUpdatePost: LiveData<String> = _responsePostUpdate

    lateinit var auth:FirebaseAuth



 fun uploadPost(uriList: List<Uri>) {
     viewModelScope.launch {
         postRepo.uploadPost(uriList)
         _responsePostUpdate.postValue(SUCCESS)
     }


    }

    private var _responsePost = MutableLiveData<String>()
    val responsePost: LiveData<String> = _responsePost

    fun post(postList: Post){

        viewModelScope.launch {
            postRepo.post(postList).addOnCompleteListener {
                if (it.isSuccessful){
                    _responsePost.postValue(SUCCESS)
                }


            }.addOnFailureListener {
                _responsePost.postValue(it.message.toString())
                Log.d("TAG",it.message.toString())
            }
        }

    }






}