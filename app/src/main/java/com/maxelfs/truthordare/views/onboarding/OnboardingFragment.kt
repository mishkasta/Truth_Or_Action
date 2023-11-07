package com.maxelfs.truthordare.views.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.maxelfs.truthordare.databinding.FragmentOnboardingBinding
import com.maxelfs.truthordare.viewmodels.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private lateinit var _viewModel : OnboardingViewModel
    private lateinit var _binding : FragmentOnboardingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel = ViewModelProvider(this)[OnboardingViewModel::class.java]
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false).apply {
            viewModel = _viewModel
        }

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.lifecycleOwner = viewLifecycleOwner

        val viewPager = _binding.onboardingViewPager
        viewPager.adapter = OnboardingPagesAdapter(this)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position != _viewModel.currentPageIndex.value!!) {
                    _viewModel.setPage(position)
                } else {
                    _viewModel.onNextPage()
                }
            }
        })

        _viewModel.navigateToStartGameEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = OnboardingFragmentDirections
                    .actionOnboardingFragmentToPackSelectionFragment()
                findNavController().navigate(direction)
                _viewModel.onNavigatedToStartGame()
            }
        }

        _viewModel.currentPageIndex.observe(viewLifecycleOwner) {
            if (viewPager.currentItem != it) {
                viewPager.currentItem = it
            }
        }
    }
}