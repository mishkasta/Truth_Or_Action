package com.maxelfs.truthordare.views

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("richText")
fun setRichText(
    textView: TextView,
    richText: String) {
    val words = richText.split(Regex("\\s"))
    val finalWords = mutableListOf<CharSequence>()
    for (word in words) {
        val regex = Regex("\\(b\\)(.*)\\(/b\\)")
        if (regex.matches(word)) {
            val match = regex.matchEntire(word)
            val value = match!!.groups[1]!!.value

            val spanned = SpannableString(value)
           /* spanned.setSpan(BackgroundColorSpan(
                Color.parseColor("#A847E4")),
                0,
                spanned.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)*/

            finalWords.add(spanned)
        } else {
            finalWords.add(word)
        }
    }

    val sb = SpannableStringBuilder()
    for (word in finalWords) {
        sb.append(word)
        sb.append(" ")
    }

    textView.text = sb.trim()
}

@BindingAdapter(value = [ "foregroundedText", "foregroundColor" ], requireAll = false)
fun setForeground(
    textView: TextView,
    foregroundedText: String,
    foregroundedColor: Int?
) {
    val words = foregroundedText.split(Regex("\\s"))
    val finalWords = mutableListOf<CharSequence>()
    for (word in words) {
        val regex = Regex("(.*)\\(f\\)(.*)\\(/f\\)(.*)")
        if (regex.matches(word)) {
            val match = regex.matchEntire(word)
            val sb = SpannableStringBuilder()

            val leftValue = match!!.groups[1]?.value
            if (leftValue != null) {
                sb.append(leftValue)
            }

            val foregroundedValue = match.groups[2]!!.value

            val spanned = SpannableString(foregroundedValue)
            val color = foregroundedColor ?: Color.parseColor("#A847E4")
            spanned.setSpan(ForegroundColorSpan(
                color),
                0,
                spanned.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            sb.append(spanned)

            val rightValue = match.groups[3]?.value
            if (rightValue != null) {
                sb.append(rightValue)
            }

            finalWords.add(sb)
        } else {
            finalWords.add(word)
        }
    }

    val sb = SpannableStringBuilder()
    for (word in finalWords) {
        sb.append(word)
        sb.append(" ")
    }

    textView.text = sb.trim()
}