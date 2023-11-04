package ey.samarin.data.repository

import ey.samarin.data.sources.local.FinanceLocalDataSource
import ey.samarin.data.sources.remote.FinanceRemoteDataSource
import ey.samarin.models.StockPreview
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
}

interface FinanceRepository : BaseRepository<FinanceLocalDataSource, FinanceRemoteDataSource> {
    suspend fun getMostActivesStocks(): List<StockPreview>
}