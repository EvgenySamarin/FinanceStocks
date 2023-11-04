package ey.samarin.data.sources.remote

import javax.inject.Inject


internal class FinanceRemoteDataSourceImpl @Inject constructor(

) : FinanceRemoteDataSource {
    override suspend fun getStocksRemote(): String {
        return "Hello from FinanceRemoteDataSourceImpl"
    }
}

/**
 * For network handling
 */
interface FinanceRemoteDataSource {
    suspend fun getStocksRemote(): String
}