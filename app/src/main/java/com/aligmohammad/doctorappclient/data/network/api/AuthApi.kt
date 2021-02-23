package com.aligmohammad.doctorappclient.data.network.api

import com.aligmohammad.doctorappclient.data.model.RegisterLoginData
import com.aligmohammad.doctorappclient.data.network.responses.LoginResponse
import com.aligmohammad.doctorappclient.data.model.UserLoginData
import okhttp3.Response

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi: BaseApi {

    @Headers("Content-Type: application/json")
    @POST("/auth/local")
    suspend fun login(@Body userLoginData: UserLoginData): LoginResponse

    @Headers("Content-Type: application/json")
    @POST("/auth/local/register")
    suspend fun register(@Body userLoginData: RegisterLoginData): LoginResponse

}