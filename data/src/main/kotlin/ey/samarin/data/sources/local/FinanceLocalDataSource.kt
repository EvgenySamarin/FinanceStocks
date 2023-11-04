package ey.samarin.data.sources.local

import javax.inject.Inject


internal class FinanceLocalDataSourceImpl @Inject constructor(

) : FinanceLocalDataSource {

    private var stocksInMemoryCache: String = ""

    override fun updateStocksCache(stocks: String) {
        stocksInMemoryCache = stocks
    }

    override fun getCachedStocks(): String = stocksInMemoryCache
}

/**
 * To cache handling
 */
interface FinanceLocalDataSource {
    fun getCachedStocks(): String
    fun updateStocksCache(stocks: String)
}