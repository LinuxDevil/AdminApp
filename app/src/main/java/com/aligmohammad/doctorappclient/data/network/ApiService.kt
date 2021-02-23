package com.aligmohammad.doctorappclient.data.network

import com.aligmohammad.doctorappclient.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class ApiService {

    suspend fun <T : Any> apiRequest(call: suspend (() -> Response<T>)): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        }
        val error = response.errorBody()?.string()
        val message = StringBuilder()
        error?.let {
            try {
                message.append(JSONObject(it).getString("message"))
            } catch (error: JSONException) {

            }
            message.append("\n")
        }
        message.append("Error code ${response.code()}")
        throw ApiException(message.toString())
    }

}