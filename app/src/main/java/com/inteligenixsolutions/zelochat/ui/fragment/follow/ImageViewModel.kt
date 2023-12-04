package com.inteligenixsolutions.zelochat.ui.fragment.follow

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inteligenixsolutions.zelochat.reposietory.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository) : ViewModel() {

    private val _uploadStatus = MutableLiveData<List<Uri>>()
    val uploadStatus: LiveData<List<Uri>> = _uploadStatus

    fun uploadImages(imageList: List<Uri>) {
        viewModelScope.launch {
          imageRepository.uploadImages(imageList)

        }
    }



}