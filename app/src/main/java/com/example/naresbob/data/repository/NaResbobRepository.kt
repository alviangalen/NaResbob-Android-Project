package com.example.naresbob.data.repository

import com.example.naresbob.data.remote.response.BaseResponse
import com.example.naresbob.data.remote.response.LoginResponse
import com.example.naresbob.data.remote.api.NaResbobApi
import javax.inject.Inject

class NaResbobRepository @Inject constructor(private val api: NaResbobApi) {

    suspend fun login(username: String, password: String): BaseResponse<LoginResponse> {
        return api.login(username, password)
    }

    suspend fun register(username: String, fullname: String, dateOfbirth: String, password: String): BaseResponse<Any> {
        return api.register(username, fullname, dateOfbirth, password)
    }
}