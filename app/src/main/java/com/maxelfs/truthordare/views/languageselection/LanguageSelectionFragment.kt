package com.maxelfs.truthordare.views.languageselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthordare.databinding.FragmentLanguageSelectionBinding
import com.maxelfs.truthordare.viewmodels.LanguageSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageSelectionFragment : Fragment() {
    private lateinit var _viewModel: LanguageSelectionViewModel
    private lateinit var _binding: FragmentLanguageSelectionBinding
    private lateinit var _languageAdapter: LanguageAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageSelectionBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[LanguageSelectionViewModel::class.java]
        _binding.viewModel = _viewModel

        val selectLanguageClickListener = LanguageClickListener {
            _viewModel.selectLanguage(it)
            _languageAdapter.notifyDataSetChanged()
        }

        _languageAdapter = LanguageAdapter(selectLanguageClickListener)
        _binding.languageSelector.adapter = _languageAdapter

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.lifecycleOwner = viewLifecycleOwner

        _viewModel.locales.observe(viewLifecycleOwner) {
            _languageAdapter.submitList(it)
        }

        _viewModel.navigateToOnboardingEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = LanguageSelectionFragmentDirections
                    .actionLanguageSelectionFragmentToOnboardingFragment()
                findNavController().navigate(direction)

                _viewModel.onNavigatedToOnboarding()
            }
        }

        _viewModel.navigateToPackSelectionEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = LanguageSelectionFragmentDirections
                    .actionLanguageSelectionFragmentToPackSelectionFragment()
                findNavController().navigate(direction)

                _viewModel.onNavigatedToPackSelection()
            }
        }
    }
}