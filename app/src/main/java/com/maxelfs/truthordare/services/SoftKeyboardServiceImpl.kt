package com.maxelfs.truthordare.services

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.maxelfs.truthordare.interfaces.ActivityProvider
import com.maxelfs.truthordare.interfaces.SoftKeyboardService
import javax.inject.Inject

class SoftKeyboardServiceImpl @Inject constructor(
    private val _activityProvider: ActivityProvider
) : SoftKeyboardService {
    private val _activity
    get() = _activityProvider.activity

    private val _inputMethodManager: InputMethodManager
    get() = _activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    private val _focusedView: View
    get() = _activity.currentFocus ?: View(_activity)


    override fun showKeyboard() {
        _inputMethodManager.showSoftInput(_focusedView, 0)
    }

    override fun hideKeyboard() {
        _inputMethodManager.hideSoftInputFromWindow(_focusedView.windowToken, 0)
    }
}