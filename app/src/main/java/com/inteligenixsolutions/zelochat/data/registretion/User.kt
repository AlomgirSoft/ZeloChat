package com.inteligenixsolutions.zelochat.data.registretion

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


data class User(


    var userId: String = "",

    val name: String = "",

    val password: String = "",

    var createdAt: Long? = null,

    var updatedAt: Long? = null,

    var image: String = "",

    var email: String = "",

    var about: String = "",

    var token: String = "",

    val status: String = "offline",

    var mobile: String? = null


)
