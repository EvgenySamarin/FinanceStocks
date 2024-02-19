package ey.samarin.domain.stocks

import ey.samarin.data.repository.FinanceRepository
import ey.samarin.models.DetailUIState
import javax.inject.Inject


internal class GetStockProfileUseCaseImpl @Inject constructor(
    private val financeRepository: FinanceRepository,
) : GetStockProfileUseCase {
    override suspend fun invoke(symbol: String): DetailUIState {
        val stockProfile = financeRepository.getStockProfile(stockSymbol = symbol)
        return DetailUIState(
            title = symbol,
            sector = stockProfile.sector,
            address = stockProfile.combinedAddress,
            longBusinessSummary = stockProfile.longBusinessSummary,
        )
    }
}

interface GetStockProfileUseCase {
    suspend operator fun invoke(symbol: String): DetailUIState
}