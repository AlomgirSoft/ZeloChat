package com.inteligenixsolutions.zelochat.reposietory

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.inteligenixsolutions.zelochat.data.chat.Chat

import com.mehedi.tukitalki.services.chat.ChatServiceImpl
import javax.inject.Inject

class ChatRepo @Inject constructor(private var service: ChatServiceImpl) {

    suspend fun sendChat(requestChat: Chat): Task<Void> {
        return service.sendChat(requestChat)
    }


    suspend fun getChats(): DatabaseReference {
        return service.getChat()
    }

}