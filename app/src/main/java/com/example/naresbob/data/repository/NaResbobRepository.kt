package com.example.naresbob.data.repository

import com.example.naresbob.data.local.datastore.NaResbobPreferences
import com.example.naresbob.data.remote.response.BaseResponse
import com.example.naresbob.data.remote.response.LoginResponse
import com.example.naresbob.data.remote.api.NaResbobApi
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NaResbobRepository @Inject constructor(
    private val api: NaResbobApi,
    private val dataStore: NaResbobPreferences
) {

    suspend fun login(username: String, password: String): BaseResponse<LoginResponse> {
        return api.login(username, password)
    }

    suspend fun register(username: String, fullname: String, dateOfbirth: String, password: String): BaseResponse<Any> {
        return api.register(username, fullname, dateOfbirth, password)
    }

    suspend fun getToken(): String {
        return dataStore.token.first() ?: ""
    }

    suspend fun getName(): String {
        return dataStore.name.first() ?: ""
    }

}