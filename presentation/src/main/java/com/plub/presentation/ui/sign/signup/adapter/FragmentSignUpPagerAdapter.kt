package com.plub.presentation.ui.sign.signup.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.plub.presentation.ui.sign.terms.TermsFragment

class FragmentSignUpPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    companion object {
        private const val SIGN_UP_SIZE = 1
    }

    override fun getItemCount(): Int = SIGN_UP_SIZE

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> TermsFragment()
        else -> throw IllegalAccessException()
    }
}