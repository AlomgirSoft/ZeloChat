package com.inteligenixsolutions.zelochat.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.inteligenixsolutions.zelochat.R

object ActiveUserOffline {

    var user:FirebaseUser=FirebaseAuth.getInstance().currentUser!!

    fun updateOnlineStatus(status: String) {

        val databaseReference =
            FirebaseDatabase.getInstance().getReference("user").child(user.uid)
        val map = HashMap<String, Any>()
        map["status"] = status
        databaseReference.updateChildren(map)
    }
object ImageUriList{
    val imageUriList = ArrayList<String>()
}
interface BackPressed{

}



}