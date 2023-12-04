package com.inteligenixsolutions.zelochat.data.image

import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Image(

    val images: List<String> = arrayListOf(),
    val date: Date = Date(),
) : Parcelable
