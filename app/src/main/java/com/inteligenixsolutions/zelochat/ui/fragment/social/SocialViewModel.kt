package com.inteligenixsolutions.zelochat.ui.fragment.social

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inteligenixsolutions.zelochat.utils.UiState
import com.mehedi.tukitalki.services.user.UserServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor( val userService:UserServiceImpl): ViewModel(){



//    fun onUploadMultipleFile(fileUris: List<Uri>, onResult: (UiState<List<Uri>>) -> Unit){
//        onResult.invoke(UiState.Loading)
//        viewModelScope.launch {
//            userService.uploadMultipleFile(fileUris,onResult)
//        }
//    }
}