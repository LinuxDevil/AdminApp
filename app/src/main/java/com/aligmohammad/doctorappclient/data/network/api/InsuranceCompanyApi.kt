package com.aligmohammad.doctorappclient.data.network.api

import com.aligmohammad.doctorappclient.data.network.responses.*
import retrofit2.http.GET

interface InsuranceCompanyApi: BaseApi {

    @GET("/insurances")
    suspend fun getInsurances(): InsuranceCompanyResponse

}