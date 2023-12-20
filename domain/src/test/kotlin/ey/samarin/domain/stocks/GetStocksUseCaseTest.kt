package ey.samarin.domain.stocks

import ey.samarin.data.repository.FinanceRepository
import ey.samarin.data.sources.local.FinanceLocalDataSource
import ey.samarin.data.sources.remote.FinanceRemoteDataSource
import ey.samarin.models.StockPreview
import ey.samarin.models.StockProfile
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.runners.Parameterized
import java.util.concurrent.TimeoutException

class GetStocksUseCaseTest {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Boolean> {
            return listOf(
                false,
                true
            )
        }

        //you should keep your mock data as close to the tests as possible
        //his way you make the tests more readable and atomic
        private val TEST_TimeoutException = TimeoutException("Test timeout exception")

        private val TEST_STUB_StockPreview = listOf(
            StockPreview(symbol = "GOOG", longName = "Alphabet Inc."),
            StockPreview(symbol = "AMZN", longName = "Amazon.com Inc."),
            StockPreview(symbol = "MSFT", longName = "Microsoft Corporation"),
        )
    }

    private lateinit var financeRepository: FinanceRepository
    private lateinit var getStocksUseCase: GetStocksUseCase

    //due to the fact that we can't use Mockito, we have to add a flag, with shall be set in every
    //test instead of just writing a Mockito `when` condition
    private var isThrowError = false

    @Before
    fun setup() {
        //It is forbidden to use third-party libraries in the test task,
        //so this test uses the explicit creation of a repository instance,
        //however, for such scenarios it is preferable to use Mockito so as not to write boilerplate code.
        //It should also be noted that you do not need to use DI for Unit tests,
        //because we need specific mock data, actually this is written in the off. documentation
        //see https://developer.android.com/training/dependency-injection/hilt-testing#unit-tests
        financeRepository = object : FinanceRepository {
            override val remoteDataSource: FinanceRemoteDataSource
                get() = TODO("Not necessary for GetStocksUseCaseTest")
            override val localDataSource: FinanceLocalDataSource
                get() = TODO("Not necessary for GetStocksUseCaseTest")

            override suspend fun getMostActivesStocks(): List<StockPreview> = if (isThrowError)
                throw TEST_TimeoutException
            else TEST_STUB_StockPreview

            override suspend fun getStockProfile(stockSymbol: String): StockProfile =
                TODO("Not necessary for GetStocksUseCaseTest")
        }
        getStocksUseCase = GetStocksUseCaseImpl(financeRepository)
    }

    @Test
    fun `checking for the expected result of GetStocksUseCase`() = runBlocking {
        isThrowError = false
        val expectedStocks = TEST_STUB_StockPreview
        //if we were using Mockito, we should also add a method getMostActivesStocks() call check
        //verify(financeRepository).getMostActivesStocks()
        val result = getStocksUseCase.invoke()
        assertEquals(expectedStocks, result)
    }

    @Test
    fun `checking for the right error passing of GetStocksUseCase`() {
        isThrowError = true
        assertThrows(TimeoutException::class.java) {
            //we use run blocking of coroutines to avoid suspending the test
            runBlocking {
                getStocksUseCase.invoke()
            }
        }
    }
}