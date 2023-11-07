package com.maxelfs.truthordare.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthordare.databinding.FragmentRateAppBinding


import com.maxelfs.truthordare.viewmodels.RateAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateAppFragment : Fragment() {
    private lateinit var _binding: FragmentRateAppBinding
    private lateinit var _viewModel: RateAppViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRateAppBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[RateAppViewModel::class.java]
        _binding.viewModel = _viewModel

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.lifecycleOwner = viewLifecycleOwner

        _viewModel.navigateBackEvent.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                _viewModel.onNavigatedBack()
            }
        }
    }
}