package com.maxelfs.truthordare.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class ViewModelBase(application: Application) : AndroidViewModel(application) {
    private val _goBackEvent = MutableLiveData<Boolean>()


    val goBackEvent: LiveData<Boolean> = _goBackEvent


    open fun goBack() {
        _goBackEvent.value = true
    }

    open fun onBack() {
        _goBackEvent.value = false
    }
}