package com.example.currencyconverter.features.converter.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.domain.model.CurrencyRate
import com.example.currencyconverter.domain.model.CurrencyType
import com.example.currencyconverter.domain.model.getRate
import com.example.currencyconverter.domain.result.doIfFailure
import com.example.currencyconverter.domain.result.doIfSuccess
import com.example.currencyconverter.domain.interactor.Interactors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val interactors: Interactors
) : ViewModel() {

    private val _originalCurrency = MutableLiveData(Currencies.BASE)
    val originalCurrency: LiveData<CurrencyType>
        get() = _originalCurrency

    private val _originalValue = MutableLiveData(1.0)
    val originalValue: LiveData<Double>
        get() = _originalValue

    private val _convertedCurrency = MutableLiveData(CurrencyType.USD)
    val convertedCurrency: LiveData<CurrencyType>
        get() = _convertedCurrency

    private val _convertedValue = MutableLiveData<Double>()
    val convertedValue: LiveData<Double>
        get() = _convertedValue

    private var rate: CurrencyRate? = null

    private val _showUnableToUpdateRateMessage = MutableLiveData(false)
    val showUnableToLoadRateMessage: LiveData<Boolean>
        get() = _showUnableToUpdateRateMessage

    private val _showUpdatedSuccessfullyMessage = MutableLiveData(false)
    val showUpdatedSuccessfullyMessage: LiveData<Boolean>
        get() = _showUpdatedSuccessfullyMessage

    private val _showLoadingMessage = MutableLiveData(false)
    val showLoadingMessage: LiveData<Boolean>
        get() = _showLoadingMessage

    init {
        updateRateFromNetwork()
        rate ?: updateRateFromLocal()
    }

    fun updateOriginalCurrency(currency: CurrencyType) {
        _originalCurrency.value = currency
    }

    fun updateConvertedCurrency(currency: CurrencyType) {
        _convertedCurrency.value = currency
    }

    fun updateOriginalValue(money: Double?) {
        money ?: return
        _originalValue.value = money
    }

    fun updateConvertedValue() {
        rate ?: return

        _convertedValue.value = rate!!.getRate(
            _originalCurrency.value!!,
            _convertedCurrency.value!!
        )!! * _originalValue.value!!
    }

    fun swapCurrencies() {
        val temp = originalCurrency.value
        _originalCurrency.value = convertedCurrency.value
        _convertedCurrency.value = temp

        updateConvertedValue()
    }

    fun doneUnableToLoadToast() {
        _showUnableToUpdateRateMessage.value = false
    }

    fun doneUpdatedToast() {
        _showUpdatedSuccessfullyMessage.value = false
    }

    fun updateRateFromNetwork() {
        viewModelScope.launch {
            _showLoadingMessage.value = true
            val result = interactors.getNetworkCurrencyRateUseCase.execute(Unit)
            _showLoadingMessage.value = false

            result.doIfSuccess { currencyRate ->
                rate = currencyRate
                updateConvertedValue()

                interactors.saveCurrencyRateUseCase.execute(rate!!)

                _showUpdatedSuccessfullyMessage.value = true
            }

            result.doIfFailure {
                _showUnableToUpdateRateMessage.value = true
            }
        }
    }

    private fun updateRateFromLocal() {
        viewModelScope.launch {
            val result = interactors.getLocalCurrencyRateUseCase.execute(Unit)

            result.doIfSuccess { localCurrencyRate ->
                rate = localCurrencyRate
                updateConvertedValue()
            }
        }
    }
}