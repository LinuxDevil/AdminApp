/*******************************************************************************
 *
 * Copyright RectiCode(c) 2020.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and de-compilation.
 *
 * Created by Ali Mohammad
 *
 ******************************************************************************/

package com.aligmohammad.doctorappclient.ui.fragments.authframent

import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aligmohammad.doctorappclient.data.network.Resource
import com.aligmohammad.doctorappclient.data.network.responses.LoginResponse
import com.aligmohammad.doctorappclient.BaseViewModel
import com.aligmohammad.doctorappclient.data.repository.AuthRepository
import com.aligmohammad.doctorappclient.util.snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AuthViewModel @ViewModelInject constructor(private val repository: AuthRepository) :
    BaseViewModel(repository) {

    private lateinit var firebaseAuth: FirebaseAuth

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun register(
        username: String,
        password: String,
        phoneNumber: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.registerUser(username, username, username)
    }

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(username, username, username)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        repository.saveAuthToken(accessToken, refreshToken)
    }

    fun logoutUser() = viewModelScope.launch {
        repository.logoutUser();
    }

    fun saveFirebaseUser(user: String) = viewModelScope.launch {
        repository.saveFirebaseUser(user)
    }

    fun verifyPhoneNumber(phoneNumber: String, activity: Activity, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        FirebaseApp.initializeApp(activity.applicationContext)
        firebaseAuth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyCode(code: String, verificationId: String, registerFunction: () -> Unit, signInFunction: () -> Unit, view: View): Boolean {
        val credentials = PhoneAuthProvider.getCredential(verificationId, code)
        var isComplete = false
        firebaseAuth.signInWithCredential(credentials).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.v("Verify Code", "Success")
//                signInFunction()
                registerFunction()
                isComplete = true
            } else {
                Log.v("Verify Code", "Failed")
                view.snackbar("Wrong Verification Code")
                isComplete = false
            }
        }
        return isComplete
    }


}