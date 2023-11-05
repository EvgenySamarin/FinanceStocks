package ey.samarin.providers.network.models


import com.google.gson.annotations.SerializedName

data class StockProfileModel(
    @SerializedName("body") val body: ProfileBody,
    @SerializedName("meta") val meta: ProfileMeta,
)

data class ProfileMeta(
    @SerializedName("copywrite") val copywrite: String,
    @SerializedName("modules") val modules: String,
    @SerializedName("processedTime") val processedTime: String,
    @SerializedName("status") val status: Int,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("version") val version: String
)

data class ProfileBody(
    @SerializedName("address1") val address1: String,
    @SerializedName("address2") val address2: String,
    @SerializedName("auditRisk") val auditRisk: Int,
    @SerializedName("boardRisk") val boardRisk: Int,
    @SerializedName("city") val city: String,
    @SerializedName("companyOfficers") val companyOfficers: List<CompanyOfficer>,
    @SerializedName("compensationAsOfEpochDate") val compensationAsOfEpochDate: Int,
    @SerializedName("compensationRisk") val compensationRisk: Int,
    @SerializedName("country") val country: String,
    @SerializedName("fullTimeEmployees") val fullTimeEmployees: Int,
    @SerializedName("governanceEpochDate") val governanceEpochDate: Int,
    @SerializedName("industry") val industry: String,
    @SerializedName("industryDisp") val industryDisp: String,
    @SerializedName("industryKey") val industryKey: String,
    @SerializedName("longBusinessSummary") val longBusinessSummary: String,
    @SerializedName("maxAge") val maxAge: Int,
    @SerializedName("overallRisk") val overallRisk: Int,
    @SerializedName("phone") val phone: String,
    @SerializedName("sector") val sector: String,
    @SerializedName("sectorDisp") val sectorDisp: String,
    @SerializedName("sectorKey") val sectorKey: String,
    @SerializedName("shareHolderRightsRisk") val shareHolderRightsRisk: Int,
    @SerializedName("state") val state: String,
    @SerializedName("website") val website: String,
    @SerializedName("zip") val zip: String
)

data class CompanyOfficer(
    @SerializedName("age") val age: Int,
    @SerializedName("exercisedValue") val exercisedValue: ExercisedValue,
    @SerializedName("fiscalYear") val fiscalYear: Int,
    @SerializedName("maxAge") val maxAge: Int,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("totalPay") val totalPay: TotalPay,
    @SerializedName("unexercisedValue") val unexercisedValue: UnexercisedValue,
    @SerializedName("yearBorn") val yearBorn: Int
)

data class UnexercisedValue(
    @SerializedName("fmt") val fmt: Any,
    @SerializedName("longFmt") val longFmt: String,
    @SerializedName("raw") val raw: Int
)

data class ExercisedValue(
    @SerializedName("fmt") val fmt: Any,
    @SerializedName("longFmt") val longFmt: String,
    @SerializedName("raw") val raw: Int
)

data class TotalPay(
    @SerializedName("fmt") val fmt: String,
    @SerializedName("longFmt") val longFmt: String,
    @SerializedName("raw") val raw: Int
)

