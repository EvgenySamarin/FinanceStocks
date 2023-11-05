package ey.samarin.data.sources.remote

import ey.samarin.models.StockPreview
import ey.samarin.models.StockProfile
import ey.samarin.providers.network.StocksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


internal class FinanceRemoteDataSourceImpl @Inject constructor(
    private val stocksApi: StocksApi,
) : FinanceRemoteDataSource {

    private val asyncCoroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()
    override suspend fun getMostActivesStocks(): List<StockPreview> =
        withContext(asyncCoroutineContext) {
            stocksApi.getMostActivesStocks()
                .body
                .map {
                    StockPreview(
                        symbol = it.symbol,
                        longName = it.longName,
                    )
                }
        }

    override suspend fun getStockProfile(symbol: String): StockProfile =
        stocksApi.getSocksProfile(stockSymbol = symbol)
            .body
            .let {
                StockProfile(
                    sector = it.sector,
                    combinedAddress = it.address1 + it.city + it.state + it.zip + it.country,
                    longBusinessSummary = it.longBusinessSummary,
                )
            }
}

/**
 * For network handling with filtering unused data, and keep in project only readable field names
 */
interface FinanceRemoteDataSource {
    suspend fun getMostActivesStocks(): List<StockPreview>

    suspend fun getStockProfile(symbol: String): StockProfile
}