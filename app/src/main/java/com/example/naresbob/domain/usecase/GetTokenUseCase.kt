package com.example.naresbob.domain.usecase

import com.example.naresbob.data.repository.NaResbobRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: NaResbobRepository
) {
    suspend operator fun invoke(): String {
        return repository.getToken()
    }
}