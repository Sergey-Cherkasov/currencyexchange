package pt.svcdev.currency.exchange.rate.repository

interface Repository<T> {
    suspend fun getData(): T
}