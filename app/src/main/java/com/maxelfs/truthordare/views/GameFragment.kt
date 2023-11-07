package com.maxelfs.truthordare.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.maxelfs.truthordare.databinding.FragmentGameBinding
import com.maxelfs.truthordare.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : FragmentBase<GameViewModel>() {
    private lateinit var _binding : FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        _binding.viewModel = viewModel

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.lifecycleOwner = viewLifecycleOwner

        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToRateAppEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = GameFragmentDirections.actionGameFragmentToRateAppFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToRateApp()
            }
        }

        viewModel.navigateToPackSelectionEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = GameFragmentDirections.actionGameFragmentToPackSelectionFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToPackSelection()
            }
        }

        viewModel.navigateToPlayersSetupEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = GameFragmentDirections.actionGameFragmentToPlayersSetupFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToPlayersSetup()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.initializeOrRestore()

        val adRequest = AdRequest.Builder().build()
        _binding.adView.loadAd(adRequest)
    }
}