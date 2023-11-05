package ey.samarin.providers.network

import ey.samarin.providers.network.models.MostActivesModel
import ey.samarin.providers.network.models.StockProfileModel
import retrofit2.http.GET
import retrofit2.http.Path

interface StocksApi {

    @GET("co/collections/most_actives")
    suspend fun getMostActivesStocks(): MostActivesModel

    @GET("qu/quote/{SYMBOL}/asset-profile")
    suspend fun getSocksProfile(@Path("SYMBOL") stockSymbol: String): StockProfileModel
}