package ey.samarin.domain.stocks

import ey.samarin.data.repository.FinanceRepository
import javax.inject.Inject

internal class GetStocksUseCaseImpl @Inject constructor(
    private val financeRepository: FinanceRepository,
) : GetStocksUseCase {
    override suspend fun invoke(): String = financeRepository.getSocks()
}

interface GetStocksUseCase {

    /**
     * @return list of stocks
     */
    suspend operator fun invoke(): String
}