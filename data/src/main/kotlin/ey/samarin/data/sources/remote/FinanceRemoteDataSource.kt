package ey.samarin.data.sources.remote

import ey.samarin.models.StockPreview
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
    override suspend fun getMostActivesStocks(): List<StockPreview> {
        return withContext(asyncCoroutineContext) {
            stocksApi.getMostActivesStocks()
                .quotes
                .map {
                    StockPreview(
                        symbol = it.symbol,
                    )
                }
        }
    }
}

/**
 * For network handling with filtering unused data, and keep in project only readable field names
 */
interface FinanceRemoteDataSource {
    suspend fun getMostActivesStocks(): List<StockPreview>
}