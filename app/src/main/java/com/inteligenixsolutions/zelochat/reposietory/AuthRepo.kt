package com.inteligenixsolutions.zelochat.reposietory

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.inteligenixsolutions.zelochat.data.login.RequestUserLogin
import com.inteligenixsolutions.zelochat.data.registretion.User

import com.mehedi.tukitalki.services.auth.AuthServiceImpl
import javax.inject.Inject

class AuthRepo @Inject constructor (private var authService: AuthServiceImpl) {




    suspend fun registration(requestUserRegister: User): Task<AuthResult> {
        return authService.register(requestUserRegister)
    }


    suspend fun login(request: RequestUserLogin): Task<AuthResult> {
        return authService.login(request)
    }


}