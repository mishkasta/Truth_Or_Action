package com.maxelfs.truthanddare.views.languageselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.maxelfs.truthanddare.models.Locale

class LanguageViewModel constructor(val locale: Locale, isSelected: Boolean)
{
    private val _isSelected = MutableLiveData<Boolean>()


    val isSelected: LiveData<Boolean> = _isSelected


    init {
        _isSelected.value = isSelected
    }


    fun setIsSelected(isSelected: Boolean) {
        _isSelected.value = isSelected
    }
}