package ey.samarin.providers.network

import ey.samarin.providers.network.models.MostActivesModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StocksApi {

    @GET("co/collections/most_actives")
    suspend fun getMostActivesStocks(): MostActivesModel

    @GET("qu/quote/{SYMBOL}/asset-profile")
    suspend fun getSocksProfile(@Path("SYMBOL") stockSymbol: String): Response<ResponseBody>
}