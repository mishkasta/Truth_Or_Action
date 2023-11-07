package com.maxelfs.truthordare.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxelfs.truthordare.databinding.FragmentPlayersSetupBinding
import com.maxelfs.truthordare.interfaces.SoftKeyboardService
import com.maxelfs.truthordare.interfaces.TooltipService
import com.maxelfs.truthordare.viewmodels.PlayersSetupViewModel
import com.maxelfs.truthordare.views.players.PlayerAdapter
import com.maxelfs.truthordare.views.players.PlayerClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayersSetupFragment : FragmentBase<PlayersSetupViewModel>() {
    private lateinit var _binding: FragmentPlayersSetupBinding
    private lateinit var _playersAdapter: PlayerAdapter
    private var _shouldChangeTextSelection: Boolean = false


    @Inject
    lateinit var tooltipService: TooltipService

    @Inject
    lateinit var keyboardService: SoftKeyboardService


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PlayersSetupViewModel::class.java]
        _binding = FragmentPlayersSetupBinding.inflate(inflater, container, false)
        _binding.viewModel = viewModel

        val startUpdatingClickListener = PlayerClickListener { player ->
            viewModel.startUpdating(player)
        }

        val deletePlayerClickListener = PlayerClickListener { player ->
            viewModel.deletePlayer(player)
        }

        _playersAdapter = PlayerAdapter(
            startUpdatingClickListener,
            deletePlayerClickListener)

        _binding.players.adapter = _playersAdapter

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToGameEvent.observe(viewLifecycleOwner) {
            if (it) {
                val direction = PlayersSetupFragmentDirections
                    .actionStartGameFragmentToGameFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToGame()
            }
        }

        viewModel.players.observe(viewLifecycleOwner) {
            _playersAdapter.submitList(it)
        }

        viewModel.isEditing.observe(viewLifecycleOwner) {
            if (it) {
                val nameEdit = _binding.nameEdit
                nameEdit.requestFocus()
                keyboardService.showKeyboard()
                _shouldChangeTextSelection = true
            } else {
                keyboardService.hideKeyboard()
            }
        }

        viewModel.editingPlayerName.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && _shouldChangeTextSelection) {
                val nameEdit = _binding.nameEdit
                val textLength = nameEdit.text.length
                nameEdit.setSelection(textLength)
                _shouldChangeTextSelection = false
            }
        }

        viewModel.editingErrorId.observe(viewLifecycleOwner) {
            if (it != -1) {
                val tooltipText = getText(it) as String
                tooltipService.showTooltip(
                    tooltipText,
                    _binding.nameEdit,
                    TooltipService.Position.ABOVE)
            } else {
                tooltipService.hideTooltip()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        viewModel.initializeOrRestore()

        if (viewModel.players.value!!.isEmpty()){
            viewModel.startPlayerCreation()
        }
    }

    override fun onStop() {
        super.onStop()

        viewModel.suspend()
    }
}