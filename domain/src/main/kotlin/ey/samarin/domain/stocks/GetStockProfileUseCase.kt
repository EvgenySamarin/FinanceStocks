package ey.samarin.domain.stocks

import ey.samarin.data.repository.FinanceRepository
import ey.samarin.models.StockProfile
import javax.inject.Inject


internal class GetStockProfileUseCaseImpl @Inject constructor(
    private val financeRepository: FinanceRepository,
) : GetStockProfileUseCase {
    override suspend fun invoke(symbol: String): StockProfile =
        financeRepository.getStockProfile(stockSymbol = symbol)
}

interface GetStockProfileUseCase {
    suspend operator fun invoke(symbol: String): StockProfile
}