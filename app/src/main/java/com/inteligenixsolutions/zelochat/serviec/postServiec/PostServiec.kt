package com.inteligenixsolutions.zelochat.serviec.postServiec

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.inteligenixsolutions.zelochat.data.post.Post

interface PostServiec {

   fun post(postList: Post): Task<Void>
    suspend fun uploadPost(uri: List<Uri>): List<String>



}