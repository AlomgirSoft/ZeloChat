package com.inteligenixsolutions.zelochat.ui.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inteligenixsolutions.zelochat.data.login.RequestUserLogin
import com.inteligenixsolutions.zelochat.reposietory.AuthRepo
import com.inteligenixsolutions.zelochat.utils.ERROR
import com.inteligenixsolutions.zelochat.utils.SUCCESS
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepo):ViewModel() {




    private var _resposne = MutableLiveData<String>()
    val responseLogin: LiveData<String> = _resposne


    fun login(
        request: RequestUserLogin
    ) {


        viewModelScope.launch {
            authRepo.login(request).addOnCompleteListener {

                if (it.isSuccessful) {
                    _resposne.postValue(SUCCESS)
                }


            }.addOnFailureListener {

                _resposne.postValue(it.localizedMessage ?: ERROR)

            }

        }


    }

}