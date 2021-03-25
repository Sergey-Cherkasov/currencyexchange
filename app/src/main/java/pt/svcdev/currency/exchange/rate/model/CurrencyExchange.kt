package pt.svcdev.currency.exchange.rate.model

import com.google.gson.annotations.SerializedName

data class CurrencyExchange(
    @field:SerializedName("code") val code: Int,
    @field:SerializedName("messageTitle") val messageTitle: String?,
    @field:SerializedName("message") val message: String?,
    @field:SerializedName("rid") val rid: String?,
    @field:SerializedName("downloadDate") val downloadDate: String?,
    @field:SerializedName("rates") val rates: List<Rates>,
    @field:SerializedName("productState") val productState: Int,
    @field:SerializedName("ratesDate") val ratesDate: String?
)