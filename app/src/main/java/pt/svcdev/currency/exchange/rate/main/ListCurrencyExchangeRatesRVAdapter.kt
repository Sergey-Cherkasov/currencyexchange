package pt.svcdev.currency.exchange.rate.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.svcdev.currency.exchange.rate.R
import pt.svcdev.currency.exchange.rate.model.CurrencyExchange
import pt.svcdev.currency.exchange.rate.model.Rates

class ListCurrencyExchangeRatesRVAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<ListCurrencyExchangeRatesRVAdapter.CurrencyExchangeViewHolder>() {

    private var data: List<Rates> = listOf()

    fun setData(data: CurrencyExchange) {
        this.data = data.rates
        notifyDataSetChanged()
    }

    inner class CurrencyExchangeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val currencyName = view.findViewById<TextView>(R.id.currency_name)
        private val currencyBuy = view.findViewById<TextView>(R.id.currency_buy)
        private val currencySale = view.findViewById<TextView>(R.id.currency_sale)

        fun bind(data: Rates) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                currencyName.text = data.name
                currencyBuy.text = data.buy
                currencySale.text = data.sale

                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyExchangeViewHolder =
        CurrencyExchangeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_currency_exchange_rate_item, parent, false) as View
        )

    override fun onBindViewHolder(holder: CurrencyExchangeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    private fun openInNewWindow(listItemData: Rates) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Rates)
    }

}