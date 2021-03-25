package pt.svcdev.currency.exchange.rate.api

import kotlinx.coroutines.Deferred
import pt.svcdev.currency.exchange.rate.model.CurrencyExchange
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("moby-44/core")
    @Headers(
        "User-Agent:MKBB Mobile iOS 3.0.0.182 (iPhone 11; iOS 14.4.1; Scale/2.00; Private)",
        "Content-Type:application/json",
        "Accept:application/json"
    )
    fun getDataAsync(
        @Query("r") r: String = "BEYkZbmV",
        @Query("d") d: String = "563B4852-6D4B-49D6-A86E-B273DD520FD2",
        @Query("t") t: String = "ExchangeRates",
        @Query("v") v: String = "44"
    ): Deferred<CurrencyExchange>
}