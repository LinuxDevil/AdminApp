package com.aligmohammad.doctorappclient.data.network

import com.aligmohammad.doctorappclient.data.network.responses.FirebaseUserResponse

object UserSingleton {

    private lateinit var _user: FirebaseUserResponse

    init {
        _user = FirebaseUserResponse(null,null,null,null,null,null,null,null,null,null,null,null, null)
    }

    fun setUser(user: FirebaseUserResponse) {
        _user = user
    }

    fun getCurrentUser(): FirebaseUserResponse {
        return _user
    }

}