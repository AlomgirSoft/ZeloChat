package com.inteligenixsolutions.zelochat.ui.fragment.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper
import com.google.firebase.database.getValue
import com.google.firebase.storage.StorageReference
import com.inteligenixsolutions.zelochat.data.user.UserProfile
import com.inteligenixsolutions.zelochat.reposietory.UserRepo
import com.inteligenixsolutions.zelochat.utils.showAlert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val storageRef: StorageReference,
    private val database: DatabaseReference,


    private val firebaseAuth: FirebaseAuth


    ) : ViewModel() {

    private var _resposne = MutableLiveData<UserProfile>()
    val responseUserProfile: LiveData<UserProfile> = _resposne


    fun getUserById(userId: String) {


        viewModelScope.launch {

            userRepo.getUserById(userId).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.getValue(UserProfile::class.java)

                    value?.let {

                        _resposne.postValue(it)
                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }

            })


        }


    }

    private var _responseAllUser = MutableLiveData<List<UserProfile>>()
    val responseAllUserProfile: LiveData<List<UserProfile>> = _responseAllUser


    fun getAllUser() {

        val userList = mutableListOf<UserProfile>()


        viewModelScope.launch {

            userRepo.getAllUser().addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()
                    for (user in snapshot.children){
                          try {
                              val value = user.getValue(UserProfile::class.java)
                              value?.let {
                                  userList.add(it)
                              }
                          }catch (e:Exception){
                              Log.d("TAG",e.message.toString())

                        }
                    }


//                    snapshot.children.forEach { dataSnapshot ->
//
//
//                    }









                    _responseAllUser.postValue(userList)
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAGA", "Failed to read value.", error.toException())
                }
            })
        }
    }

    fun uploadImage(uri: Uri, context: Context) {


        val currentUser = firebaseAuth.currentUser



        val reference = storageRef.child("image${currentUser!!.uid}")

        val uploadTask = reference.putFile(uri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                showAlert(context, "task success")
                task.exception?.let {
                    showAlert(context, it.message.toString())
                }
            }
            return@continueWithTask reference.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                val imageUri = downloadUri.toString()
                val map = HashMap<String, Any>()
                map["image"] = imageUri


                database.child(currentUser.uid).updateChildren(map)
                    .addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {

                            showAlert(
                                context,
                                "task database success"
                            )// Handle success, e.g., navigate to the main activity
                        } else {
                            showAlert(context, "task error")// Handle failure
                        }
                    }.addOnFailureListener { e ->

                        showAlert(context, e.message.toString())
                        Log.d("TAG",e.message.toString())
                    }


            }


        }


    }



}

