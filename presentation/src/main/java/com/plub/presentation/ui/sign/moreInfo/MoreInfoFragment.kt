package com.plub.presentation.ui.sign.moreInfo

import androidx.fragment.app.viewModels
import com.plub.domain.model.enums.SignUpPageType
import com.plub.presentation.state.MoreInfoPageState
import com.plub.presentation.base.BaseFragment
import com.plub.presentation.databinding.FragmentMoreInfoBinding
import com.plub.presentation.ui.sign.signup.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreInfoFragment : BaseFragment<FragmentMoreInfoBinding, MoreInfoPageState, MoreInfoViewModel>(
    FragmentMoreInfoBinding::inflate
) {

    override val viewModel: MoreInfoViewModel by viewModels()
    private val parentViewModel: SignUpViewModel by viewModels({requireParentFragment()})

    override fun initView() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun initStates() {
        super.initStates()

        repeatOnStarted(viewLifecycleOwner) {

            launch {
                viewModel.moveToNextPage.collect {
                    parentViewModel.onMoveToNextPage(SignUpPageType.MORE_INFO, it)
                }
            }

            launch {
                parentViewModel.uiState.collect {
                    viewModel.onSaveNickname(it.profileComposeVo.nickname)
                    viewModel.onInitMoreInfo(it.moreInfoVo)
                }
            }
        }
    }
}