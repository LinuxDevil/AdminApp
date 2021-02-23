package com.aligmohammad.doctorappclient.data.network

import android.content.Context
import com.aligmohammad.doctorappclient.data.network.api.TokenRefreshApi
import com.aligmohammad.doctorappclient.data.network.repository.BaseRepository
import com.aligmohammad.doctorappclient.data.network.responses.TokenResponse
import com.aligmohammad.doctorappclient.helpers.PreferencesStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    context: Context,
    private val tokenApi: TokenRefreshApi
) : Authenticator, BaseRepository(tokenApi) {

    private val appContext = context.applicationContext
    private val userPreferences = PreferencesStore(appContext)

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is Resource.Success -> {
                    userPreferences.saveAccessTokens(
                        tokenResponse.value.access_token!!,
                        tokenResponse.value.refresh_token!!
                    )
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokenResponse.value.access_token}")
                        .build()
                }
                else -> null
            }
        }
    }

    private suspend fun getUpdatedToken(): Resource<TokenResponse> {
        var refreshToken: String = ""
        userPreferences.refreshToken.collect {
            refreshToken = it!!
        }
        return safeApiCall { tokenApi.refreshAccessToken(refreshToken) }
    }

}