package com.mehedi.tukitalki.services.chat


import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.inteligenixsolutions.zelochat.data.chat.Chat


interface ChatService {
    suspend fun sendChat(request: Chat): Task<Void>

    suspend fun getChat(): DatabaseReference


}