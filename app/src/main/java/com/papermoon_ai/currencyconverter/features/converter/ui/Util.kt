package com.papermoon_ai.currencyconverter.features.converter.ui

import android.text.Editable
import android.text.SpannableStringBuilder
import java.text.DecimalFormat

fun numberToEditable(number: Double): Editable {
    return SpannableStringBuilder(
        DecimalFormat("0.#####")
            .format(number).replace(',', '.')
    )
}