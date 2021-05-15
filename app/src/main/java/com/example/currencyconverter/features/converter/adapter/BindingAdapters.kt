package com.example.currencyconverter.features.converter.adapter

import androidx.databinding.BindingAdapter
import com.example.currencyconverter.features.converter.ui.numberToEditable
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("roundMoney")
fun TextInputEditText.bindMoney(number: Double) {
    text = numberToEditable(number)
}