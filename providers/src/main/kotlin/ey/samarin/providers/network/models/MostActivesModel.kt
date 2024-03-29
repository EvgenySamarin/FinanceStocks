package ey.samarin.providers.network.models


import com.google.gson.annotations.SerializedName

data class MostActivesModel(
    @SerializedName("body") val body: List<Body>,
    @SerializedName("meta") val meta: Meta,
)

data class Meta(
    @SerializedName("copywrite") val copywrite: String = "",
    @SerializedName("count") val count: Int = 0,
    @SerializedName("description") val description: String = "",
    @SerializedName("offset") val offset: Int = 0,
    @SerializedName("processedTime") val processedTime: String = "",
    @SerializedName("status") val status: Int = 0,
    @SerializedName("total") val total: Int = 0,
    @SerializedName("version") val version: String = ""
)

data class Body(
    @SerializedName("ask") val ask: Double = 0.0,
    @SerializedName("askSize") val askSize: Int = 0,
    @SerializedName("averageAnalystRating") val averageAnalystRating: String = "",
    @SerializedName("averageDailyVolume10Day") val averageDailyVolume10Day: Int = 0,
    @SerializedName("averageDailyVolume3Month") val averageDailyVolume3Month: Int = 0,
    @SerializedName("bid") val bid: Double = 0.0,
    @SerializedName("bidSize") val bidSize: Int = 0,
    @SerializedName("bookValue") val bookValue: Double = 0.0,
    @SerializedName("cryptoTradeable") val cryptoTradeable: Boolean = false,
    @SerializedName("currency") val currency: String = "",
    @SerializedName("customPriceAlertConfidence") val customPriceAlertConfidence: String = "",
    @SerializedName("displayName") val displayName: String = "",
    @SerializedName("dividendDate") val dividendDate: Int = 0,
    @SerializedName("dividendRate") val dividendRate: Double = 0.0,
    @SerializedName("dividendYield") val dividendYield: Double = 0.0,
    @SerializedName("earningsTimestamp") val earningsTimestamp: Int = 0,
    @SerializedName("earningsTimestampEnd") val earningsTimestampEnd: Int = 0,
    @SerializedName("earningsTimestampStart") val earningsTimestampStart: Int = 0,
    @SerializedName("epsCurrentYear") val epsCurrentYear: Double = 0.0,
    @SerializedName("epsForward") val epsForward: Double = 0.0,
    @SerializedName("epsTrailingTwelveMonths") val epsTrailingTwelveMonths: Double = 0.0,
    @SerializedName("esgPopulated") val esgPopulated: Boolean = false,
    @SerializedName("exchange") val exchange: String = "",
    @SerializedName("exchangeDataDelayedBy") val exchangeDataDelayedBy: Int = 0,
    @SerializedName("exchangeTimezoneName") val exchangeTimezoneName: String = "",
    @SerializedName("exchangeTimezoneShortName") val exchangeTimezoneShortName: String = "",
    @SerializedName("fiftyDayAverage") val fiftyDayAverage: Double = 0.0,
    @SerializedName("fiftyDayAverageChange") val fiftyDayAverageChange: Double = 0.0,
    @SerializedName("fiftyDayAverageChangePercent") val fiftyDayAverageChangePercent: Double = 0.0,
    @SerializedName("fiftyTwoWeekChangePercent") val fiftyTwoWeekChangePercent: Double = 0.0,
    @SerializedName("fiftyTwoWeekHigh") val fiftyTwoWeekHigh: Double = 0.0,
    @SerializedName("fiftyTwoWeekHighChange") val fiftyTwoWeekHighChange: Double = 0.0,
    @SerializedName("fiftyTwoWeekHighChangePercent") val fiftyTwoWeekHighChangePercent: Double = 0.0,
    @SerializedName("fiftyTwoWeekLow") val fiftyTwoWeekLow: Double = 0.0,
    @SerializedName("fiftyTwoWeekLowChange") val fiftyTwoWeekLowChange: Double = 0.0,
    @SerializedName("fiftyTwoWeekLowChangePercent") val fiftyTwoWeekLowChangePercent: Double = 0.0,
    @SerializedName("fiftyTwoWeekRange") val fiftyTwoWeekRange: String = "",
    @SerializedName("financialCurrency") val financialCurrency: String = "",
    @SerializedName("firstTradeDateMilliseconds") val firstTradeDateMilliseconds: Long = 0L,
    @SerializedName("forwardPE") val forwardPE: Double = 0.0,
    @SerializedName("fullExchangeName") val fullExchangeName: String = "",
    @SerializedName("gmtOffSetMilliseconds") val gmtOffSetMilliseconds: Int = 0,
    @SerializedName("ipoExpectedDate") val ipoExpectedDate: String = "",
    @SerializedName("language") val language: String = "",
    @SerializedName("lastClosePriceToNNWCPerShare") val lastClosePriceToNNWCPerShare: Double = 0.0,
    @SerializedName("lastCloseTevEbitLtm") val lastCloseTevEbitLtm: Double = 0.0,
    @SerializedName("longName") val longName: String = "",
    @SerializedName("market") val market: String = "",
    @SerializedName("marketCap") val marketCap: Long = 0L,
    @SerializedName("marketState") val marketState: String = "",
    @SerializedName("messageBoardId") val messageBoardId: String = "",
    @SerializedName("nameChangeDate") val nameChangeDate: String = "",
    @SerializedName("postMarketChange") val postMarketChange: Double = 0.0,
    @SerializedName("postMarketChangePercent") val postMarketChangePercent: Double = 0.0,
    @SerializedName("postMarketPrice") val postMarketPrice: Double = 0.0,
    @SerializedName("postMarketTime") val postMarketTime: Int = 0,
    @SerializedName("forwardPE") val prevName: String = "",
    @SerializedName("priceEpsCurrentYear") val priceEpsCurrentYear: Double = 0.0,
    @SerializedName("priceHint") val priceHint: Int = 0,
    @SerializedName("priceToBook") val priceToBook: Double = 0.0,
    @SerializedName("quoteSourceName") val quoteSourceName: String = "",
    @SerializedName("quoteType") val quoteType: String = "",
    @SerializedName("region") val region: String = "",
    @SerializedName("regularMarketChange") val regularMarketChange: Double = 0.0,
    @SerializedName("regularMarketChangePercent") val regularMarketChangePercent: Double = 0.0,
    @SerializedName("regularMarketDayHigh") val regularMarketDayHigh: Double = 0.0,
    @SerializedName("regularMarketDayLow") val regularMarketDayLow: Double = 0.0,
    @SerializedName("regularMarketDayRange") val regularMarketDayRange: String = "",
    @SerializedName("regularMarketOpen") val regularMarketOpen: Double = 0.0,
    @SerializedName("regularMarketPreviousClose") val regularMarketPreviousClose: Double = 0.0,
    @SerializedName("regularMarketPrice") val regularMarketPrice: Double = 0.0,
    @SerializedName("regularMarketTime") val regularMarketTime: Int = 0,
    @SerializedName("regularMarketVolume") val regularMarketVolume: Int = 0,
    @SerializedName("sharesOutstanding") val sharesOutstanding: Long = 0L,
    @SerializedName("shortName") val shortName: String = "",
    @SerializedName("sourceInterval") val sourceInterval: Int = 0,
    @SerializedName("symbol") val symbol: String = "",
    @SerializedName("tradeable") val tradeable: Boolean = false,
    @SerializedName("trailingAnnualDividendRate") val trailingAnnualDividendRate: Double = 0.0,
    @SerializedName("trailingAnnualDividendYield") val trailingAnnualDividendYield: Double = 0.0,
    @SerializedName("trailingPE") val trailingPE: Double = 0.0,
    @SerializedName("triggerable") val triggerable: Boolean = false,
    @SerializedName("twoHundredDayAverage") val twoHundredDayAverage: Double = 0.0,
    @SerializedName("twoHundredDayAverageChange") val twoHundredDayAverageChange: Double = 0.0,
    @SerializedName("twoHundredDayAverageChangePercent") val twoHundredDayAverageChangePercent: Double = 0.0,
    @SerializedName("typeDisp") val typeDisp: String = "",
) 

