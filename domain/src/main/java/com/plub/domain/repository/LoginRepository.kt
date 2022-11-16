package com.plub.domain.repository

import com.plub.domain.UiState
import com.plub.domain.model.vo.login.SampleLogin
import com.plub.domain.model.vo.login.SocialLoginRequestVo
import com.plub.domain.model.vo.login.SocialLoginResponseVo
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun socialLogin(request: SocialLoginRequestVo): Flow<UiState<SocialLoginResponseVo>>
}