package com.plub.domain.usecase

import com.plub.domain.base.UseCase
import com.plub.domain.repository.PlubJwtRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPlubRefreshTokenUseCase @Inject constructor(
    private val plubJwtRepository: PlubJwtRepository
):UseCase<Unit, Flow<String>>() {
    override operator fun invoke(request: Unit): Flow<String> {
        return plubJwtRepository.getRefreshToken()
    }
}