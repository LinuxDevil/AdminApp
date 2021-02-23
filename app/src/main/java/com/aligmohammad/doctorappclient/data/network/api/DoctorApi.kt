package com.aligmohammad.doctorappclient.data.network.api

import com.aligmohammad.doctorappclient.data.model.Doctor
import com.aligmohammad.doctorappclient.data.network.NetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers


interface DoctorApi : BaseApi {

    @GET("/doctors/all")
    suspend fun getAllDoctors(): List<Doctor>

    @Headers("Content-Type: application/json")
    @GET("/doctors/getById")
    suspend fun getDoctorById(@Body id: String): Doctor

    companion object {
        operator fun invoke(networkInterceptor: NetworkInterceptor): DoctorApi {
            return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(networkInterceptor).build())
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(DoctorApi::class.java)
        }
    }

}