package com.inteligenixsolutions.zelochat.data.post

import android.net.Uri

data class Post(
    val postByI: String = "",
    val houseName: String = "",
    val houseNumber: String = "",
    val houseAddress: String = "",
    val phoneNumber: String = "",
    val postUsi: List<String> = emptyList()
)
