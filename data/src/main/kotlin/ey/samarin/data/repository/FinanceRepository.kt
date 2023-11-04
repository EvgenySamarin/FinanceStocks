package ey.samarin.data.repository

import ey.samarin.data.sources.local.FinanceLocalDataSource
import ey.samarin.data.sources.remote.FinanceRemoteDataSource
import javax.inject.Inject


internal class FinanceRepositoryImpl @Inject constructor(
    override val remoteDataSource: FinanceRemoteDataSource,
    override val localDataSource: FinanceLocalDataSource,
) : FinanceRepository {
    override suspend fun getSocks(): String {
        val remoteStocks = remoteDataSource.getStocksRemote()
        localDataSource.updateStocksCache(remoteStocks)
        return localDataSource.getCachedStocks()
    }
}

interface FinanceRepository : BaseRepository<FinanceLocalDataSource, FinanceRemoteDataSource> {
    // TODO [202311308]: change this to actual method
    suspend fun getSocks(): String
}