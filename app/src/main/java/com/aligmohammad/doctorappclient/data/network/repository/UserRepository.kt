package com.aligmohammad.doctorappclient.data.network.repository

import com.aligmohammad.doctorappclient.data.model.UserInfoModel
import com.aligmohammad.doctorappclient.data.network.api.UserApi
import com.aligmohammad.doctorappclient.data.network.responses.UserInfoResponse
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserApi): BaseRepository(api) {

    suspend fun addUserInfo(userInfo: UserInfoModel) = safeApiCall {
        api.addUserDetails(userInfo)
    }

}