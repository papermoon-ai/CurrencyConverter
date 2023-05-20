package com.papermoon.currencyconverter.features.selector.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.papermoon.currencyconverter.databinding.FragmentSelectorBinding
import com.papermoon.currencyconverter.domain.model.CurrencyType
import com.papermoon.currencyconverter.features.selector.adapter.CurrencyListAdapter
import com.papermoon.currencyconverter.features.selector.adapter.OnClickListener

class SelectorFragment() : Fragment() {

    private var _binding: FragmentSelectorBinding? = null
    private val binding: FragmentSelectorBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSelectorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args by navArgs<SelectorFragmentArgs>()

        val adapter = CurrencyListAdapter(CurrencyType.values(), OnClickListener {
            val callback = args.callback
            callback.onCurrencySelected(it)

            findNavController().navigate(SelectorFragmentDirections.actionSelectorFragmentToConverterFragment())
        })

        binding.currencyList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
