package com.papermoon_ai.currencyconverter.features.callback

import android.os.Parcel
import android.os.Parcelable
import com.papermoon_ai.currencyconverter.domain.model.CurrencyType

abstract class SelectorResultCallback : Parcelable {

    abstract fun onCurrencySelected(currency: CurrencyType)

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) = Unit
}