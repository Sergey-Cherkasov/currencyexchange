package pt.svcdev.currency.exchange.rate.repository

interface DataSource<T> {
    suspend fun getData(): T
}