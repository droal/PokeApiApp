package com.example.pokeapilulo.util

import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan

fun List<String>.toPointList(): SpannableStringBuilder {

   val spannableStringBuilder = SpannableStringBuilder()
    this.forEachIndexed { index, string ->
        val line = string + if(index < this.size - 1) "\n" else ""
        val spannable = SpannableString(line)
        spannable.setSpan(
            BulletSpan(20, Color.BLUE),
            0,
            spannable.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        spannableStringBuilder.append(spannable)

    }

    return spannableStringBuilder
}