package com.mehedi.tukitalki.services.user



import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.inteligenixsolutions.zelochat.data.post.Post

import com.inteligenixsolutions.zelochat.data.registretion.User
import com.inteligenixsolutions.zelochat.utils.FirebaseStorageConstants.POST_IMAGES
import com.inteligenixsolutions.zelochat.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val dbRef: FirebaseDatabase,

    val database: FirebaseStorage,
    val dbReference: DatabaseReference,
    var  auth: FirebaseAuth,
    val storageReference: StorageReference
) : UserService {

    override suspend fun createUser(request: User): Task<Void> {

        return dbRef.reference.child("user").child(request.userId).setValue(request)
    }

    override suspend fun getUserById(userId: String): DatabaseReference {

        return dbRef.reference.child("user").child(userId)
    }

    override suspend fun getUserAllUser(): DatabaseReference {


        return dbRef.reference.child("user")


    }




}