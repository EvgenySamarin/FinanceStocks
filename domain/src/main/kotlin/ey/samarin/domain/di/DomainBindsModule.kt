@file:Suppress("unused")

package ey.samarin.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ey.samarin.domain.stocks.GetStocksUseCase
import ey.samarin.domain.stocks.GetStocksUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainBindsModule {
    @Binds
    abstract fun bindGetStocksUseCase(
        getStocksUseCaseImpl: GetStocksUseCaseImpl,
    ): GetStocksUseCase

}