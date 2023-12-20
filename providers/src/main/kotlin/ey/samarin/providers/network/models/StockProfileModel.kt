package ey.samarin.providers.network.models


import com.google.gson.annotations.SerializedName

data class StockProfileModel(
    @SerializedName("body") val body: ProfileBody,
    @SerializedName("meta") val meta: ProfileMeta,
)

data class ProfileMeta(
    @SerializedName("copywrite") val copywrite: String = "",
    @SerializedName("modules") val modules: String = "",
    @SerializedName("processedTime") val processedTime: String = "",
    @SerializedName("status") val status: Int = 0,
    @SerializedName("symbol") val symbol: String = "",
    @SerializedName("version") val version: String = ""
)

data class ProfileBody(
    @SerializedName("address1") val address1: String = "",
    @SerializedName("address2") val address2: String = "",
    @SerializedName("auditRisk") val auditRisk: Int = 0,
    @SerializedName("boardRisk") val boardRisk: Int = 0,
    @SerializedName("city") val city: String = "",
    @SerializedName("companyOfficers") val companyOfficers: List<CompanyOfficer>? = null,
    @SerializedName("compensationAsOfEpochDate") val compensationAsOfEpochDate: Int = 0,
    @SerializedName("compensationRisk") val compensationRisk: Int = 0,
    @SerializedName("country") val country: String = "",
    @SerializedName("fullTimeEmployees") val fullTimeEmployees: Int = 0,
    @SerializedName("governanceEpochDate") val governanceEpochDate: Int = 0,
    @SerializedName("industry") val industry: String = "",
    @SerializedName("industryDisp") val industryDisp: String = "",
    @SerializedName("industryKey") val industryKey: String = "",
    @SerializedName("longBusinessSummary") val longBusinessSummary: String = "",
    @SerializedName("maxAge") val maxAge: Int = 0,
    @SerializedName("overallRisk") val overallRisk: Int = 0,
    @SerializedName("phone") val phone: String = "",
    @SerializedName("sector") val sector: String = "",
    @SerializedName("sectorDisp") val sectorDisp: String = "",
    @SerializedName("sectorKey") val sectorKey: String = "",
    @SerializedName("shareHolderRightsRisk") val shareHolderRightsRisk: Int = 0,
    @SerializedName("state") val state: String = "",
    @SerializedName("website") val website: String = "",
    @SerializedName("zip") val zip: String = ""
)

data class CompanyOfficer(
    @SerializedName("age") val age: Int = 0,
    @SerializedName("exercisedValue") val exercisedValue: ExercisedValue,
    @SerializedName("fiscalYear") val fiscalYear: Int = 0,
    @SerializedName("maxAge") val maxAge: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("totalPay") val totalPay: TotalPay,
    @SerializedName("unexercisedValue") val unexercisedValue: UnexercisedValue,
    @SerializedName("yearBorn") val yearBorn: Int = 0
)

data class UnexercisedValue(
    @SerializedName("fmt") val fmt: Any,
    @SerializedName("longFmt") val longFmt: String = "",
    @SerializedName("raw") val raw: Int = 0
)

data class ExercisedValue(
    @SerializedName("fmt") val fmt: Any,
    @SerializedName("longFmt") val longFmt: String = "",
    @SerializedName("raw") val raw: Int = 0
)

data class TotalPay(
    @SerializedName("fmt") val fmt: String = "",
    @SerializedName("longFmt") val longFmt: String = "",
    @SerializedName("raw") val raw: Int = 0
)

