package com.mehedi.tukitalki.services.auth


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.inteligenixsolutions.zelochat.data.login.RequestUserLogin
import com.inteligenixsolutions.zelochat.data.registretion.User

interface AuthService {

    suspend fun register(request: User): Task<AuthResult>

  suspend fun login(request: RequestUserLogin): Task<AuthResult>


}