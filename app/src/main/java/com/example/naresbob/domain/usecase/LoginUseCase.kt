package com.example.naresbob.domain.usecase

import com.example.naresbob.data.mapper.toUserDomain
import com.example.naresbob.data.repository.NaResbobRepository
import com.example.naresbob.domain.model.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: NaResbobRepository) {
    suspend operator fun invoke(username: String, password: String) : Result<User> {
        return try {
            val response = repo.login(username, password)
            if (response.status.equals("success") && response.data != null) {
                val user = response.data.toUserDomain()
                Result.success(user)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
