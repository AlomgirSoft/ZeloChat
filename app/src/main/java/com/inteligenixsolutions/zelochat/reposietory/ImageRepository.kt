package com.inteligenixsolutions.zelochat.reposietory

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.core.Context
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(private val firebaseStorage: FirebaseStorage, private val database:DatabaseReference) {



  lateinit var  auth:FirebaseAuth
    suspend fun uploadImages(imageList: List<Uri>): List<String> {
        val  currentUser=auth.currentUser
        val downloadUrls = mutableListOf<String>()

        withContext(Dispatchers.IO) {
            // Upload each image
            imageList.forEach { uri ->
                val fileName = "image_${System.currentTimeMillis()}.jpg"
                val storageRef = firebaseStorage.reference.child("images/${currentUser}")
                storageRef.putFile(uri).await()

                // Get download URL
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    downloadUrls.add(uri.toString())


                  //  database.child("POST_IMAGES").child(currentUser.toString()).




                    Log.d("TAG",downloadUrls.toString())

                }.await()
            }
        }

        return downloadUrls
    }
}