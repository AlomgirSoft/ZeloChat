package com.inteligenixsolutions.zelochat.serviec.postServiec

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.inteligenixsolutions.zelochat.data.post.Post
import com.inteligenixsolutions.zelochat.utils.ActiveUserOffline.ImageUriList.imageUriList
import com.inteligenixsolutions.zelochat.utils.FirebaseStorageConstants.POST_IMAGES
import javax.inject.Inject

class PostServiceIml @Inject constructor(private val dbRef: FirebaseDatabase, val auth:FirebaseAuth, val databaseReference: DatabaseReference): PostServiec {
    val currentUserUid = auth.currentUser?.uid
    //val imageUriList = ArrayList<String>()
    override fun post(postList: Post): Task<Void> {


        return if (currentUserUid != null) {
            dbRef.reference.child("POST_IMAGES")
                .child(currentUserUid)
                .setValue(postList).addOnCompleteListener {
                    if (it.isSuccessful){

                    }
                }

        } else {
            Tasks.forException(Exception("User not signed in"))
        }
    }

    override suspend fun uploadPost(uriList: List<Uri>): List<String> {

        val currentUser = auth.currentUser

        val reference = FirebaseStorage.getInstance().reference.child(POST_IMAGES)
        for (uri in uriList) {
            val ref = reference.child(uri.lastPathSegment.toString())
            ref.putFile(uri).addOnCompleteListener { image ->
                if (image.isSuccessful) {
                    ref.downloadUrl.addOnSuccessListener {
              imageUriList.add(it.toString())

                        Log.d("TAG", imageUriList.toString())


                    }
                }
            }
        }
        return imageUriList
    }


//    private fun uploadImageUris(imageUris: List<String>) {
//        // Upload image URIs to the "postUri" child node
//        databaseReference.child("postUri").setValue(imageUris)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Image URIs uploaded successfully
//                } else {
//                    // Handle failure
//                }
//            }
//
//    }


}