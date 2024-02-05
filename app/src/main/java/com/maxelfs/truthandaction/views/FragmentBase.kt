package com.maxelfs.truthandaction.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthandaction.viewmodels.ViewModelBase

abstract class FragmentBase<TViewModel : ViewModelBase> : Fragment() {
    protected lateinit var viewModel : TViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.goBackEvent.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                viewModel.onBack()
            }
        }
    }
}