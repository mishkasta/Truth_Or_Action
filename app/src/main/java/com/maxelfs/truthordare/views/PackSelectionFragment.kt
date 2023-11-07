package com.maxelfs.truthordare.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthordare.databinding.FragmentPackSelectionBinding
import com.maxelfs.truthordare.viewmodels.PackSelectionViewModel
import com.maxelfs.truthordare.views.packs.PackAdapter
import com.maxelfs.truthordare.views.packs.PackClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PackSelectionFragment : Fragment() {
    private lateinit var _binding: FragmentPackSelectionBinding
    private lateinit var _viewModel: PackSelectionViewModel
    private lateinit var _packAdapter: PackAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPackSelectionBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[PackSelectionViewModel::class.java]
        _binding.viewModel = _viewModel

        val startGameClickListener = PackClickListener {
            pack -> _viewModel.startGame(pack.pack)
        }

        _packAdapter = PackAdapter(startGameClickListener)
        _binding.packs.adapter = _packAdapter

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.lifecycleOwner = viewLifecycleOwner

        _viewModel.packs.observe(viewLifecycleOwner) {
            _packAdapter.submitList(it)
        }

        _viewModel.navigateToPlayerSelectionEvent.observe(viewLifecycleOwner) {
            if (it) {
                val navigationDirection = PackSelectionFragmentDirections
                    .actionPackSelectionFragmentToStartGameFragment()
                findNavController().navigate(navigationDirection)
                _viewModel.onNavigatedToPlayerSelection()
            }
        }

        _viewModel.navigateToLanguageSelectionEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = PackSelectionFragmentDirections
                    .actionPackSelectionFragmentToLanguageSelectionFragment()
                findNavController().navigate(direction)

                _viewModel.onNavigatedToLanguageSelection()
            }
        }

        _viewModel.navigateToRateAppEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = PackSelectionFragmentDirections
                    .actionPackSelectionFragmentToRateAppFragment()
                findNavController().navigate(direction)

                _viewModel.onNavigatedToRateApp()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        _viewModel.initializeOrRestore()
    }
}