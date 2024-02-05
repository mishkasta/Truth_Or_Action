package com.maxelfs.truthandaction.views.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.maxelfs.truthandaction.R
import com.maxelfs.truthandaction.databinding.PlayerItemBinding
import com.maxelfs.truthandaction.models.Gender
import com.maxelfs.truthandaction.models.Player
import com.maxelfs.truthandaction.views.ViewHolderBase

internal class PlayerViewHolder private constructor (
    private val _binding : PlayerItemBinding,
    private val _updateClickListener : PlayerClickListener,
    private val _deleteClickListener : PlayerClickListener
) : ViewHolderBase<Player>(_binding.root) {
    override fun bind(item : Player, itemsCount: Int) {
        _binding.player = item
        _binding.updateClickListener = _updateClickListener
        _binding.deleteClickListener = _deleteClickListener

        _binding.deleteButton.visibility = if (itemsCount > 1) {
            View.VISIBLE
        } else {
            View.GONE
        }

        if(item.gender == Gender.MALE){
            _binding.genderButtonMale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_male_select))
            _binding.genderButtonFemale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_female))
        }else{
            _binding.genderButtonMale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_male))
            _binding.genderButtonFemale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_female_select))
        }

        _binding.genderButtonMale.setOnClickListener {
            if(item.gender == Gender.FEMALE){
                _binding.genderButtonMale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_male_select))
                _binding.genderButtonFemale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_female))
                item.gender = Gender.MALE
            }
        }

        _binding.genderButtonFemale.setOnClickListener{
            if(item.gender == Gender.MALE){
                _binding.genderButtonMale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_male))
                _binding.genderButtonFemale.setImageDrawable(ContextCompat.getDrawable(_binding.root.context, R.drawable.ic_female_select))
                item.gender = Gender.FEMALE
            }
        }


        _binding.executePendingBindings()
    }


    companion object {
        fun from(
            parent: ViewGroup,
            updateNameClickListener: PlayerClickListener,
            deleteClickListener: PlayerClickListener) : PlayerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PlayerItemBinding.inflate(inflater, parent, false)

            return PlayerViewHolder(
                binding,
                updateNameClickListener,
                deleteClickListener)
        }
    }
}