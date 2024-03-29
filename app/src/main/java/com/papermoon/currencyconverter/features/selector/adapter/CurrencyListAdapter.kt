package com.papermoon.currencyconverter.features.selector.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.papermoon.currencyconverter.databinding.ItemCurrencyBinding
import com.papermoon.currencyconverter.domain.model.CurrencyType


class CurrencyListAdapter(
    private val currencies: Array<CurrencyType>,
    private val clickListener: OnClickListener
) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: CurrencyType) {
            binding.currency = currency
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCurrencyBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = currencies[position]
        holder.itemView.setOnClickListener {
            clickListener.onClick(currency)
        }
        holder.bind(currency)
    }

    override fun getItemCount(): Int {
        return currencies.size
    }
}

class OnClickListener(val clickListener: (currency: CurrencyType) -> Unit) {
    fun onClick(currency: CurrencyType) = clickListener(currency)
}
