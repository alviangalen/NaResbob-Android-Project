package com.example.naresbob.data.repository

import com.example.naresbob.data.remote.response.BaseResponse
import com.example.naresbob.data.remote.response.LoginResponse
import com.example.naresbob.data.remote.api.NaResbobApi
import javax.inject.Inject

class NaResbobRepository @Inject constructor(private val apiService: NaResbobApi) {

    suspend fun login(username: String, password: String): BaseResponse<LoginResponse> {
        return apiService.login(username, password)
    }
}