package com.inteligenixsolutions.zelochat.ui.fragment.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inteligenixsolutions.zelochat.data.registretion.RequestUserRegister
import com.inteligenixsolutions.zelochat.reposietory.AuthRepo
import com.inteligenixsolutions.zelochat.reposietory.UserRepo
import com.inteligenixsolutions.zelochat.utils.ERROR
import com.inteligenixsolutions.zelochat.utils.SUCCESS

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: AuthRepo,
    private val userRepo: UserRepo

    ) : ViewModel() {

    private var _resposne = MutableLiveData<String>()
    val responseRegistration: LiveData<String> = _resposne


    fun registration(
        requestUserRegister: RequestUserRegister
    ) {


        viewModelScope.launch {
            repo.registration(requestUserRegister).addOnCompleteListener {
                if (it.isSuccessful)
                {
                    requestUserRegister.userId= it.result.user!!.uid
                    viewModelScope.launch {
                        userRepo.createUser(requestUserRegister).addOnCompleteListener { dbIt->
                            if (dbIt.isSuccessful)
                            {
                              _resposne.postValue(SUCCESS)
                            }else{
                                _resposne.postValue(ERROR)
                            }

                        }
                    }

                }


            }.addOnFailureListener {
                Log.d("TAG", "${it.localizedMessage}: ")



                _resposne.postValue(it.localizedMessage ?: ERROR)

            }

        }


    }


}