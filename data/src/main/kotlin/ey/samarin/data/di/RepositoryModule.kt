package ey.samarin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ey.samarin.data.repository.FinanceRepository
import ey.samarin.data.repository.FinanceRepositoryImpl
import ey.samarin.data.sources.local.FinanceLocalDataSource
import ey.samarin.data.sources.remote.FinanceRemoteDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideFinanceRepository(
        financeRemoteDataSource: FinanceRemoteDataSource,
        financeLocalDataSource: FinanceLocalDataSource,
    ): FinanceRepository = FinanceRepositoryImpl(
        remoteDataSource = financeRemoteDataSource,
        localDataSource = financeLocalDataSource,
    )
}