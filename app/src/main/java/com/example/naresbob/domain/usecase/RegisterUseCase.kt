package com.example.naresbob.domain.usecase

import com.example.naresbob.data.repository.NaResbobRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repo: NaResbobRepository) {
    suspend operator fun invoke(username: String, fullname: String, dateOfbirth: String, password: String) : Result<String> {
        return try {
            val response = repo.register(username, fullname, dateOfbirth, password)

            if (response.status.equals("success") && response.data != null) {
                Result.success(response.message)
            } else {
                Result.failure(Exception(response.message))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}