package ey.samarin.data.repository

import ey.samarin.data.sources.local.FinanceLocalDataSource
import ey.samarin.data.sources.remote.FinanceRemoteDataSource
import ey.samarin.models.StockPreview
import ey.samarin.models.StockProfile
import javax.inject.Inject


internal class FinanceRepositoryImpl @Inject constructor(
    override val remoteDataSource: FinanceRemoteDataSource,
    override val localDataSource: FinanceLocalDataSource,
) : FinanceRepository {
    override suspend fun getMostActivesStocks(): List<StockPreview> {
        val remoteStocks = remoteDataSource.getMostActivesStocks()
        localDataSource.updateStocksPreviewCache(remoteStocks)
        return localDataSource.getCachedStocksPreview()
    }

    override suspend fun getStockProfile(stockSymbol: String): StockProfile {
        //example if we need directly online data
        return remoteDataSource.getStockProfile(symbol = stockSymbol)
    }


}

interface FinanceRepository : BaseRepository<FinanceLocalDataSource, FinanceRemoteDataSource> {
    suspend fun getMostActivesStocks(): List<StockPreview>
    suspend fun getStockProfile(stockSymbol: String): StockProfile
}