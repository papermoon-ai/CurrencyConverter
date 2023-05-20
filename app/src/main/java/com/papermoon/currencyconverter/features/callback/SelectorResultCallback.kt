package com.papermoon.currencyconverter.features.callback

import android.os.Parcel
import android.os.Parcelable
import com.papermoon.currencyconverter.domain.model.CurrencyType

abstract class SelectorResultCallback : Parcelable {

    abstract fun onCurrencySelected(currency: CurrencyType)

    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel, p1: Int) = Unit
}
