package com.inteligenixsolutions.zelochat.reposietory


import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

import com.inteligenixsolutions.zelochat.data.registretion.User
import com.inteligenixsolutions.zelochat.utils.FirebaseStorageConstants

import com.mehedi.tukitalki.services.user.UserServiceImpl
import javax.inject.Inject

class UserRepo @Inject constructor(private var service: UserServiceImpl) {


    suspend fun createUser(requestUserRegister: User): Task<Void> {

        return service.createUser(requestUserRegister)

    }

    suspend fun getUserById(userId: String): DatabaseReference {
        return service.getUserById(userId)

    }

    suspend fun getAllUser(): DatabaseReference {
        return service.getUserAllUser()

    }





}