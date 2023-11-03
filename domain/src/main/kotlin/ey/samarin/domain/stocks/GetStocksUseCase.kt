package ey.samarin.domain.stocks

import javax.inject.Inject

class GetStocksUseCaseImpl @Inject constructor() : GetStocksUseCase {
    override suspend fun invoke(): String {
        // TODO [202311308]: implement use case logic here
        return "string from domain"
    }
}

interface GetStocksUseCase {

    /**
     * @return list of stocks
     */
    suspend operator fun invoke(): String
}