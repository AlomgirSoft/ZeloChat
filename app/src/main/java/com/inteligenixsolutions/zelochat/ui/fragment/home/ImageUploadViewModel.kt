package com.inteligenixsolutions.zelochat.ui.fragment.home

import android.content.Context

import android.net.Uri

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

import com.google.firebase.storage.StorageReference
import com.inteligenixsolutions.zelochat.utils.showAlert

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

class ImageUploadViewModel (
  ) {

//    fun uploadImage(uri: Uri,context: Context) {
//        private val database: DatabaseReference,
//        private val storage: StorageReference,
//        private val firebaseAuth: FirebaseAuth
//
//        val currentUser = firebaseAuth.currentUser
//        if (currentUser == null) {
//
//
//        }
//
//
//        val reference = storage.child("image${System.currentTimeMillis()}")
//
//        val uploadTask = reference.putFile(uri)
//
//        uploadTask.continueWithTask { task ->
//            if (!task.isSuccessful) {
//               showAlert(context,"task success")
//                task.exception?.let {
//                    showAlert(context,it.message.toString())
//                }
//            }
//            return@continueWithTask reference.downloadUrl
//        }.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val downloadUri = task.result
//                val imageUri = downloadUri.toString()
//                val  map =HashMap<String,Any>()
//                map["image"] = imageUri
//
//
//                database .child(currentUser!!.uid).updateChildren(map)
//                    .addOnCompleteListener { updateTask ->
//                        if (updateTask.isSuccessful) {
//
//                            showAlert(context,"task success")// Handle success, e.g., navigate to the main activity
//                        } else {
//                            showAlert(context,"task success")// Handle failure
//                        }
//                    }
//            } else {
//                showAlert(context," task err")// Handle failure
//            }
//
//
//        }
//
//
//
//
//}

}