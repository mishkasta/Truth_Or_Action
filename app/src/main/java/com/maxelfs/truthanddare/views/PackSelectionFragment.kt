package com.maxelfs.truthanddare.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthanddare.databinding.FragmentPackSelectionBinding
import com.maxelfs.truthanddare.viewmodels.PackSelectionViewModel
import com.maxelfs.truthanddare.views.packs.PackAdapter
import com.maxelfs.truthanddare.views.packs.PackClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PackSelectionFragment : Fragment() {
    private lateinit var _binding: FragmentPackSelectionBinding
    private lateinit var _viewModel: PackSelectionViewModel
    private lateinit var _packAdapter: PackAdapter
    private lateinit var _packAdapterHorizontal: PackAdapter

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
        _packAdapterHorizontal = PackAdapter(startGameClickListener)

        _binding.packs.adapter = _packAdapter
        _binding.packsHorizontal.adapter = _packAdapterHorizontal

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.lifecycleOwner = viewLifecycleOwner

        _viewModel.packs.observe(viewLifecycleOwner) {
            _packAdapter.submitList(it)
        }

        _viewModel.packsHorizontal.observe(viewLifecycleOwner){
            _packAdapterHorizontal.submitList(it)
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

        _viewModel.appSelectToMarketFirst.observe(viewLifecycleOwner) {
            if (it) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.maxelfs.truthordare"))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.maxelfs.truthordare"))
                    startActivity(intent)
                }
            }
        }

        _viewModel.appSelectToMarketSecond.observe(viewLifecycleOwner) {
            if (it) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.maxelfs.truthoraction"))
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.maxelfs.truthoraction"))
                    startActivity(intent)
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        _viewModel.initializeOrRestore()
    }
}