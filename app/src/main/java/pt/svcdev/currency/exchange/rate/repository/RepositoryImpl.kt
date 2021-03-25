package pt.svcdev.currency.exchange.rate.repository

import pt.svcdev.currency.exchange.rate.model.CurrencyExchange

class RepositoryImpl(private val datasource: DataSource<CurrencyExchange>) :
    Repository<CurrencyExchange> {
    override suspend fun getData(): CurrencyExchange = datasource.getData()
}