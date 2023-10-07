package com.mehedi.tukitalki.services.user


import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

import com.inteligenixsolutions.zelochat.data.registretion.RequestUserRegister


interface UserService {
    suspend fun createUser(request: RequestUserRegister): Task<Void>

    suspend fun getUserById(userId: String): DatabaseReference
    suspend fun getUserAllUser(): DatabaseReference

    suspend fun uploadProfileImage(uri: Uri, strRef: StorageReference): UploadTask


}