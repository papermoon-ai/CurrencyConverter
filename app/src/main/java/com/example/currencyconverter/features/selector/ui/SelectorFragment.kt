package com.example.currencyconverter.features.selector.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currencyconverter.databinding.SelectorFragmentBinding
import com.example.currencyconverter.domain.model.CurrencyType
import com.example.currencyconverter.features.selector.adapter.CurrencyListAdapter
import com.example.currencyconverter.features.selector.adapter.OnClickListener

class SelectorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SelectorFragmentBinding.inflate(inflater)

        val args by navArgs<SelectorFragmentArgs>()

        val adapter = CurrencyListAdapter(CurrencyType.values(), OnClickListener {
            val callback = args.callback
            callback.onCurrencySelected(it)

            findNavController().navigate(SelectorFragmentDirections.actionSelectorFragmentToConverterFragment())
        })

        binding.currencyList.adapter = adapter

        return binding.root
    }
}