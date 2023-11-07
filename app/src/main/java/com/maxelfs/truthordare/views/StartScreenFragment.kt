package com.maxelfs.truthordare.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.viewmodels.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartScreenFragment : Fragment() {
    private lateinit var _viewModel : SplashScreenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this)[SplashScreenViewModel::class.java]

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewModel.navigateToStartEvent.observe(viewLifecycleOwner) {
            if (it) {
                val action = StartScreenFragmentDirections
                    .actionSplashScreenFragmentToPackSelectionFragment()
                findNavController().navigate(action)

                _viewModel.onNavigatedToStart()
            }
        }

        _viewModel.navigateToOnboardingEvent.observe(viewLifecycleOwner) {
            if (it) {
                val action = StartScreenFragmentDirections
                    .actionSplashScreenFragmentToOnboardingFragment()
                findNavController().navigate(action)

                _viewModel.onNavigatedToOnboarding()
            }
        }

        _viewModel.navigateToLanguageSelectionEvent.observe(viewLifecycleOwner) {
            if (it) {
                val action = StartScreenFragmentDirections
                    .actionSplashScreenFragmentToLanguageSelectionFragment()
                findNavController().navigate(action)

                _viewModel.onNavigatedToLanguageSelection()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        _viewModel.navigateNext()
    }
}