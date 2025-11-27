package com.example.naresbob.data.mapper

import com.example.naresbob.data.remote.response.LoginResponse
import com.example.naresbob.domain.model.User

fun LoginResponse.toUserDomain(): User {
    return User(
        id = this.user?.id ?: 0,
        name = this.user?.name.orEmpty(),
        username = this.user?.username.orEmpty(),
        image = this.user?.image.orEmpty(),
        token = this.token.orEmpty()
    )
}