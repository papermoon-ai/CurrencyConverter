package com.example.currencyconverter.features.converter.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.*
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.ConverterFragmentBinding
import com.example.currencyconverter.domain.model.CurrencyType
import com.example.currencyconverter.features.callback.OnChangeCurrencyListener
import com.example.currencyconverter.features.callback.SelectorResultCallback
import com.example.currencyconverter.features.converter.vm.ConverterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private val viewModel: ConverterViewModel by activityViewModels()

    companion object {
        private const val successMessage = "Updated successfully!"
        private const val loadingMessage = "Loading..."
        private const val failureMessage = "Unable to update currency rate"
        private const val noAvailableRatesMessage = "No currency rates available"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ConverterFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.originalMoneyEditText.editText?.text =
            numberToEditable(viewModel.originalValue.value!!)

        binding.originalMoneyEditText.editText?.doAfterTextChanged { value ->
            try {
                viewModel.updateOriginalValue(value.toString().toDouble())
            } catch (exception: NumberFormatException) {
                viewModel.updateOriginalValue(0.0)
            }
        }

        viewModel.originalValue.observe(viewLifecycleOwner, {
            viewModel.updateConvertedValue()
        })


        viewModel.showUpdatedSuccessfullyMessage.observe(viewLifecycleOwner, { showSuccessMessage ->
            if (showSuccessMessage) {
                Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show()
                viewModel.doneUpdatedToast()
            }
        })

        viewModel.showUnableToLoadRateMessage.observe(viewLifecycleOwner, { showErrorMessage ->
            if (showErrorMessage) {
                Toast.makeText(context, failureMessage, Toast.LENGTH_SHORT).show()
                viewModel.doneUnableToLoadToast()
            }
        })

        viewModel.showLoadingMessage.observe(viewLifecycleOwner, { showLoadingMessage ->
            if (showLoadingMessage) {
                binding.convertedCurrencyTextView.editText?.text = SpannableStringBuilder(
                    loadingMessage
                )
            } else {
                viewModel.convertedValue.value?.let { value ->
                    binding.convertedCurrencyTextView.editText?.text =
                        numberToEditable(value)
                    return@observe
                }

                binding.convertedCurrencyTextView.editText?.text = SpannableStringBuilder(
                    noAvailableRatesMessage
                )
            }
        })

        binding.changeOriginalCurrencyButton.setOnClickListener {
            onChangeCurrencyListener(object : OnChangeCurrencyListener {
                override fun changeCurrency(currency: CurrencyType) {
                    viewModel.updateOriginalCurrency(currency)
                }
            })
        }

        binding.changeConvertedCurrencyButton.setOnClickListener {
            onChangeCurrencyListener(object : OnChangeCurrencyListener {
                override fun changeCurrency(currency: CurrencyType) {
                    viewModel.updateConvertedCurrency(currency)
                }
            })
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun onChangeCurrencyListener(listener: OnChangeCurrencyListener) {
        findNavController().navigate(
            ConverterFragmentDirections.actionConverterFragmentToSelectorFragment(
                object : SelectorResultCallback() {
                    override fun onCurrencySelected(currency: CurrencyType) {
                        listener.changeCurrency(currency)
                        viewModel.updateConvertedValue()
                    }
                }
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update_button -> {
                viewModel.updateRateFromNetwork()
                return true
            }
        }
        return false
    }
}