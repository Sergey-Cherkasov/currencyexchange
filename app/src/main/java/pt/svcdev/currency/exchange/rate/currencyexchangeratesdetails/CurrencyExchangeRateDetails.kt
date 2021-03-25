package pt.svcdev.currency.exchange.rate.currencyexchangeratesdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import pt.svcdev.currency.exchange.rate.databinding.CurrencyExchangeRateDetailsBinding
import pt.svcdev.currency.exchange.rate.model.Rates

class CurrencyExchangeRateDetails : AppCompatActivity() {

    private lateinit var binding: CurrencyExchangeRateDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CurrencyExchangeRateDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionbarHomeButtonUp()
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setData() {
        val data = intent.extras?.getParcelable<Rates>(DATA_EXTRA)
        binding.name.text = data?.name
        binding.currMemFrom.text = data?.currMnemFrom
        binding.currMemTo.text = data?.currMnemTo
        binding.from.text = data?.from.toString()
        binding.to.text = data?.to.toString()
        binding.buy.text = data?.buy
        binding.deltaBuy.text = data?.deltaBuy
        binding.sale.text = data?.sale
        binding.deltaSale.text = data?.deltaSale
    }

    private fun setActionbarHomeButtonUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {

        private const val DATA_EXTRA = "976ae7b4-62ef-4b74-b2da-c1a77cf168c4"

        fun getIntent(context: Context, data: Rates): Intent =
                Intent(context, CurrencyExchangeRateDetails::class.java).apply {
                    putExtra(DATA_EXTRA, data)
                }
    }

}