package com.inteligenixsolutions.zelochat.reposietory

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.inteligenixsolutions.zelochat.data.post.Post
import com.inteligenixsolutions.zelochat.serviec.postServiec.PostServiceIml
import javax.inject.Inject

class PostRepo  @Inject constructor(val postIml: PostServiceIml) {


    suspend fun post(postList: Post): Task<Void>{
        return postIml.post(postList)
    }

    suspend fun uploadPost(uriList: List<Uri>): List<String> {

        return postIml.uploadPost(uriList)
    }

}