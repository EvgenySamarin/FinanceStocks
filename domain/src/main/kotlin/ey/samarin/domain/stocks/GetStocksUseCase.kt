package ey.samarin.domain.stocks

import ey.samarin.data.repository.FinanceRepository
import ey.samarin.models.StockPreview
import javax.inject.Inject


internal class GetStocksUseCaseImpl @Inject constructor(
    private val financeRepository: FinanceRepository,
) : GetStocksUseCase {
    override suspend fun invoke(): List<StockPreview> = financeRepository.getMostActivesStocks()
}

interface GetStocksUseCase {

    /**
     * @return list of stocks
     */
    suspend operator fun invoke(): List<StockPreview>
}