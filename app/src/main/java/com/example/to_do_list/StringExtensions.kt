package com.example.to_do_list

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan

fun String.addStrikeThrough () : SpannableString {
    val spannable = SpannableString(this)

        spannable.setSpan(StrikethroughSpan(), 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannable
}

fun String.addBoldText (): SpannableString {
    val spannable = SpannableString(this)

    spannable.setSpan(StyleSpan(Typeface.BOLD), 0, this.length, 0)
    return spannable

}