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

package com.aligmohammad.doctorappclient.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T): Resource<T>()
    data class Failure(
        val isNetwork: Boolean?,
        val errorCode: Int?,
        val errorResponse: ResponseBody?
    ) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
