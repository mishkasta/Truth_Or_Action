package com.maxelfs.truthordare.views.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.viewmodels.MAX_PAGE_INDEX

class OnboardingPagesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = MAX_PAGE_INDEX + 1

    override fun createFragment(position: Int): Fragment {
        val textId = when (position) {
            0 -> R.string.onboarding_page_1
            1 -> R.string.onboarding_page_2
            2 -> R.string.onboarding_page_3
            else -> R.string.lets_go
        }
        val imageId = when (position) {
            0 -> R.drawable.ic_onboarding_page_new_1
            1 -> R.drawable.ic_onboarding_page_new_2
            else -> R.drawable.ic_onboarding_page_new_3
        }

        val pageFragment = OnboardingPageFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TEXT_ID, textId)
                putInt(ARG_IMAGE_ID, imageId)
            }
        }

        return pageFragment
    }
}