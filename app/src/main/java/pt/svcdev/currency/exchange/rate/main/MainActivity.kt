package pt.svcdev.currency.exchange.rate.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.svcdev.currency.exchange.rate.R
import pt.svcdev.currency.exchange.rate.currencyexchangeratesdetails.CurrencyExchangeRateDetails
import pt.svcdev.currency.exchange.rate.model.AppState
import pt.svcdev.currency.exchange.rate.model.CurrencyExchange
import pt.svcdev.currency.exchange.rate.model.Rates
import pt.svcdev.currency.exchange.rate.repository.RepositoryImpl
import pt.svcdev.currency.exchange.rate.retrofit.RetrofitImpl

class MainActivity : AppCompatActivity() {

    private val listCurrencyExchangeRates: RecyclerView by lazy {
        findViewById(R.id.list_currency_exchange_rate)
    }
    private val adapter: ListCurrencyExchangeRatesRVAdapter by lazy {
        ListCurrencyExchangeRatesRVAdapter(onListItemClickListener)
    }
    private lateinit var viewModel: MainViewModel

    private val repository: RepositoryImpl by lazy {
        RepositoryImpl(RetrofitImpl())
    }

    private val onListItemClickListener: ListCurrencyExchangeRatesRVAdapter.OnListItemClickListener =
        object : ListCurrencyExchangeRatesRVAdapter.OnListItemClickListener {
            override fun onItemClick(data: Rates) {
                startActivity(
                    CurrencyExchangeRateDetails.getIntent(this@MainActivity, data)
                )
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        viewModel.getData()
        initViews()
    }

    private fun initViewModel() {
        if (listCurrencyExchangeRates.adapter != null)
            throw IllegalStateException("The ViewModel should be initialised first")
        viewModel = MainViewModel(repository)
        viewModel.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        listCurrencyExchangeRates.layoutManager = LinearLayoutManager(applicationContext)
        listCurrencyExchangeRates.adapter = adapter
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.data?.let {
                    setDataToAdapter(it)
                }
            }
            is AppState.Error -> {
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${appState.error.message}", Toast.LENGTH_LONG
                ).show()
                Log.d("CURRENCY_EXCHANGE", "${appState.error.message}")
            }
        }
    }

    private fun setDataToAdapter(data: CurrencyExchange) {
        adapter.setData(data)
    }

}
