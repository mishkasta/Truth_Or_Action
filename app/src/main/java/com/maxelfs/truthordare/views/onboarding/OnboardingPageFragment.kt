package com.maxelfs.truthordare.views.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.maxelfs.truthordare.R

const val ARG_TEXT_ID = "textId"
const val ARG_IMAGE_ID = "imageId"

class OnboardingPageFragment : Fragment() {
    private var _textId: Int? = null
    private var _imageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _textId = it.getInt(ARG_TEXT_ID)
            _imageId = it.getInt(ARG_IMAGE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onboardingText = view.findViewById<TextView>(R.id.viewDescription)
        _textId?.let {
            onboardingText.setText(it)
        }

        val onboardingImage = view.findViewById<ImageView>(R.id.onboardingImage)
        _imageId?.let {
            onboardingImage.setImageResource(it)
        }
    }
}