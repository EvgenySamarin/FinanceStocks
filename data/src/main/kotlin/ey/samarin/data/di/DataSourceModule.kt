package ey.samarin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ey.samarin.data.sources.local.FinanceLocalDataSource
import ey.samarin.data.sources.local.FinanceLocalDataSourceImpl
import ey.samarin.data.sources.remote.FinanceRemoteDataSource
import ey.samarin.data.sources.remote.FinanceRemoteDataSourceImpl
import ey.samarin.providers.network.StocksApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideFinanceRemoteDataSource(
        stocksApi: StocksApi,
    ): FinanceRemoteDataSource = FinanceRemoteDataSourceImpl(
        stocksApi = stocksApi,
    )

    @Provides
    @Singleton
    fun provideFinanceLocalDataSource(): FinanceLocalDataSource = FinanceLocalDataSourceImpl()
}