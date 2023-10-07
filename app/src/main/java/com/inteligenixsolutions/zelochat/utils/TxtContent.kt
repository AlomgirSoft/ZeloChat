package com.inteligenixsolutions.zelochat.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

const val SUCCESS="Task success"
const val ERROR="Task success"
const val CHATS="chat"

const val DummyImgLink =""




fun  showAlert(context: Context, msg:String){

    val alertDialogBuilder = AlertDialog.Builder(context)
    alertDialogBuilder.setTitle("ConfirmExit!! ")
    alertDialogBuilder.setMessage(msg)


    // Create and show the dialog
    val alertDialog = alertDialogBuilder.create()
    alertDialog.show()

}


var user:FirebaseUser=FirebaseAuth.getInstance().currentUser!!

fun updateOnlineStatus(status: String) {

    val databaseReference =
        FirebaseDatabase.getInstance().getReference("user").child(user.uid)
    val map = HashMap<String, Any>()
    map["status"] = status
    databaseReference.updateChildren(map)
}

