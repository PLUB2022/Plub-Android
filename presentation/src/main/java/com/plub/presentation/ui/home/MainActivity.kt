package com.plub.presentation.ui.home

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.plub.domain.model.state.PageState
import com.plub.domain.model.state.SampleHomeState
import com.plub.presentation.R
import com.plub.presentation.base.BaseActivity
import com.plub.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, PageState.Default, MainViewModel>(ActivityMainBinding::inflate) {
    override val viewModel : MainViewModel  by viewModels()
    private lateinit var navController: NavController

    override fun initView() {
        binding.apply {
            vm = viewModel
            initNavigation()
        }
    }

    override fun initState() {
        super.initState()


    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_main_host) as NavHostFragment
        navController = navHostFragment.navController
    }

}