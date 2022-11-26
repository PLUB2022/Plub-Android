package com.plub.domain.usecase

import com.plub.domain.UiState
import com.plub.domain.base.UseCase
import com.plub.domain.model.vo.jwt_token.PlubJwtReIssueRequestVo
import com.plub.domain.model.vo.jwt_token.PlubJwtResponseVo
import com.plub.domain.repository.PlubJwtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostReIssueTokenUseCase @Inject constructor(
    private val plubJwtRepository: PlubJwtRepository
):UseCase<PlubJwtReIssueRequestVo, Flow<PlubJwtResponseVo>>() {
    override operator fun invoke(request: PlubJwtReIssueRequestVo): Flow<PlubJwtResponseVo> {
        return plubJwtRepository.reIssueToken(request).map {
            when(it) {
                is UiState.Success -> it.data
                else -> PlubJwtResponseVo("","")
            }
        }
    }
}