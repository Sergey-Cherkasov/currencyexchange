package pt.svcdev.currency.exchange.rate.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pt.svcdev.currency.exchange.rate.api.ApiService
import pt.svcdev.currency.exchange.rate.model.CurrencyExchange
import pt.svcdev.currency.exchange.rate.repository.DataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : DataSource<CurrencyExchange> {

    override suspend fun getData(): CurrencyExchange = getService().getDataAsync().await()

    private fun getService(): ApiService = createRetrofit().create(ApiService::class.java)

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_LOCATIONS)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(createOkHttpClient())
        .build()

    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

}

private const val URL_LOCATIONS = "https://mb1.as50464.net:443"