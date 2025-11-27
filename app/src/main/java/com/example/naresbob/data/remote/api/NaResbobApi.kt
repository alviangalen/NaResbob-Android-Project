package com.example.naresbob.data.remote.api

import com.example.naresbob.data.remote.response.BaseResponse
import com.example.naresbob.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NaResbobApi {

    @FormUrlEncoded
    @POST("auth/login.php")
    suspend fun login (
        @Field("username") username: String,
        @Field("password") password: String
    ) : BaseResponse<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register.php")
    suspend fun register (
        @Field("username") username: String,
        @Field("fullname") fullname: String,
        @Field("dateofbirth") dateofbirth: String,
        @Field("password") password: String
    ) : BaseResponse<Any>


}