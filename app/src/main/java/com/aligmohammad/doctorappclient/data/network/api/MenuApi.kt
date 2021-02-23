package com.aligmohammad.doctorappclient.data.network.api

import com.aligmohammad.doctorappclient.data.network.responses.MenuItemResponse
import retrofit2.http.GET


interface MenuApi: BaseApi {

    @GET("/menu-items")
    suspend fun getMenuItems(): MenuItemResponse

}