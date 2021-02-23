/*******************************************************************************
 *
 * Copyright RectiCode(c) 2021.
 * All Rights Reserved
 *
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and de-compilation.
 *
 * Created by Ali Mohammad
 *
 ******************************************************************************/

package com.aligmohammad.doctorappclient.data.network.repository

import com.aligmohammad.doctorappclient.data.network.api.AuthApi
import com.aligmohammad.doctorappclient.data.network.Resource
import com.aligmohammad.doctorappclient.data.network.api.BaseApi
import com.aligmohammad.doctorappclient.data.network.api.SafeApiCall
import com.aligmohammad.doctorappclient.data.network.responses.UserInfoResponse
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository(private val api: BaseApi): SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }

}