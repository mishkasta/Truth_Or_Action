package com.maxelfs.truthanddare.views

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.maxelfs.truthanddare.R
import com.maxelfs.truthanddare.databinding.FragmentGameBinding
import com.maxelfs.truthanddare.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class GameFragment : FragmentBase<GameViewModel>() {
    private lateinit var _binding : FragmentGameBinding
    lateinit var textToSpeech: TextToSpeech

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
                textToSpeech.shutdown()
                val direction = GameFragmentDirections.actionGameFragmentToRateAppFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToRateApp()
            }
        }

        viewModel.navigateToPackSelectionEvent.observe(viewLifecycleOwner) {
            if (it) {
                textToSpeech.shutdown()
                val direction = GameFragmentDirections.actionGameFragmentToPackSelectionFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToPackSelection()
            }
        }

        viewModel.navigateToPlayersSetupEvent.observe(viewLifecycleOwner) {
            if (it) {
                textToSpeech.shutdown()
                val direction = GameFragmentDirections.actionGameFragmentToPlayersSetupFragment()
                findNavController().navigate(direction)
                viewModel.onNavigatedToPlayersSetup()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.initializeOrRestore()
        textToSpeech = TextToSpeech(context) {
            if (it != TextToSpeech.SUCCESS) {
                _binding.voiceButton.visibility = View.GONE
            } else {
                val lang = context!!.getString(R.string.current_locale)
                if (lang == "en") {
                    textToSpeech.language = Locale.ENGLISH
                } else if (lang == "ru") {
                    textToSpeech.language = Locale.forLanguageTag("ru")
                }
            }
        }

        _binding.voiceButton.setOnClickListener {
            textToSpeech.speak(_binding.actionText.text.toString(), TextToSpeech.QUEUE_FLUSH, null)

        }

        val adRequest = AdRequest.Builder().build()
        _binding.adView.loadAd(adRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textToSpeech.shutdown()

    }
}