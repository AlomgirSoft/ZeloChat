package com.inteligenixsolutions.zelochat.data.chat

data class Chat
    (
    var senderID: String = "",
    var receiverID: String = "",
    var message: String = "",
    var chatID: String = "",
    var timeStamp: Long = 0


)