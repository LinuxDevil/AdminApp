package com.aligmohammad.doctorappclient.data.network.api

import com.aligmohammad.doctorappclient.data.model.UserInfoModel
import com.aligmohammad.doctorappclient.data.model.UserInfoModelInsurance
import com.aligmohammad.doctorappclient.data.model.UserInfoModelNationalId
import com.aligmohammad.doctorappclient.data.network.responses.UserInfoResponse
import retrofit2.http.*

interface UserApi: BaseApi {

    @Headers("Content-Type: application/json")
    @POST("/user-infos")
    suspend fun addUserDetails(@Body userDetails: UserInfoModel): UserInfoResponse

    @Headers("Content-Type: application/json")
    @PUT("/user-infos")
    suspend fun addUserDetails(@Query("id") id: String, @Body userDetails: UserInfoModelNationalId): UserInfoResponse

    @Headers("Content-Type: application/json")
    @PUT("/user-infos")
    suspend fun updateUserDetails(@Query("id") id: String, @Body userDetails: UserInfoModelInsurance): UserInfoResponse

}