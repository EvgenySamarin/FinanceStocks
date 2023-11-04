package ey.samarin.data.sources.local

import ey.samarin.models.StockPreview
import javax.inject.Inject


internal class FinanceLocalDataSourceImpl @Inject constructor(

) : FinanceLocalDataSource {
    // TODO [202311309]: change this to more efficient cache
    private var stocksInMemoryCache: List<StockPreview> = emptyList()

    override fun updateStocksPreviewCache(stocksPreview: List<StockPreview>) {
        stocksInMemoryCache = stocksPreview
    }

    override fun getCachedStocksPreview(): List<StockPreview> = stocksInMemoryCache
}

/**
 * To cache handling
 */
interface FinanceLocalDataSource {
    fun getCachedStocksPreview(): List<StockPreview>
    fun updateStocksPreviewCache(stocksPreview: List<StockPreview>)
}