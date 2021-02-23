package com.aligmohammad.doctorappclient.data.network.repository

import android.util.Log
import com.aligmohammad.doctorappclient.data.model.RegisterLoginData
import com.aligmohammad.doctorappclient.data.model.UserLoginData
import com.aligmohammad.doctorappclient.data.network.api.AuthApi
import com.aligmohammad.doctorappclient.helpers.PreferencesStore
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: PreferencesStore
) : BaseRepository(api) {

    suspend fun login(username: String, password: String, phoneNumber: String) = safeApiCall {
        api.login(UserLoginData(username, password))
    }

    suspend fun registerUser(username: String, password: String, phoneNumber: String) = safeApiCall {
        val email = username.trim() + "@myclinic.com"
        api.register(RegisterLoginData(username, password, phoneNumber, email))
    }

    suspend fun saveFirebaseUser(user: String) {
        preferences.saveFirebaseUser(user)
    }

    suspend fun saveAuthToken(token: String, refToken: String) {
        preferences.saveAccessTokens(token, refToken)
    }

    suspend fun logoutUser() {
        preferences.clear()
    }

}