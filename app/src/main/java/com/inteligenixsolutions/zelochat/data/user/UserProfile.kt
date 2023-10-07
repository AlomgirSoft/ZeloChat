package com.inteligenixsolutions.zelochat.data.user


data class UserProfile(
    var userId: String="",
    var createdAt: Long? = null,
    var updatedAt: Long? = null,
    var image: String = "",
    var name: String = "",
    var email: String = "",
    var about: String = "",
    var token: String = "",
    val status: String = "",
    var mobile: String? = null


)
