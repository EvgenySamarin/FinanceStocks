package ey.samarin.providers.di

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ey.samarin.exceptions.ProjectExceptions
import ey.samarin.providers.BuildConfig
import ey.samarin.providers.network.AssetsCertificateStore
import ey.samarin.providers.network.StocksApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        private const val DEFAULT_CONNECTION_TIMEOUT_IN_SEC = 60L
        private const val DEFAULT_READ_TIMEOUT_IN_SEC = 30L

        //Here you can set the supported SSL protocols
        private val SUPPORTED_SSL_PROTOCOLS = arrayOf("TLSv1.3", "TLSv1.2")
    }

    @Provides
    @Singleton
    fun provideAssetsCertificateStore(
        @ApplicationContext context: Context,
    ): AssetsCertificateStore {
        return AssetsCertificateStore(context)
    }

    @Provides
    @Singleton
    @Named("certified")
    fun provideX509TrustManager(assetsCertificateStore: AssetsCertificateStore): X509TrustManager {
        val originalTrustManagerFactory = TrustManagerFactory.getInstance("X509")
        originalTrustManagerFactory.init(assetsCertificateStore.keyStore)
        return originalTrustManagerFactory.trustManagers[0] as X509TrustManager
    }

    @Provides
    @Singleton
    @Named("unsafe")
    @SuppressLint("CustomX509TrustManager")
    fun provideUnsafeX509TrustManager(): X509TrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit
    }

    @Provides
    @Singleton
    fun provideSSLContext(
        //pass here the certified X509TrustManager to support right way of SSL connection
        @Named("unsafe") trustManager: X509TrustManager,
    ): SSLContext {
        for (sslProtocol in SUPPORTED_SSL_PROTOCOLS) {
            try {
                return SSLContext.getInstance(sslProtocol).also { sslContext ->
                    sslContext.init(
                        /* km = */ null,
                        /* tm = */ arrayOf(trustManager),
                        /* random = */ null
                    )
                }
            } catch (e: KeyManagementException) {
                throw ProjectExceptions.SSLContextException("SSL protocol: $sslProtocol is not supported by this device")
            } catch (e: NoSuchAlgorithmException) {
                throw ProjectExceptions.SSLContextException("SSL protocol: $sslProtocol is not supported by this device")
            }
        }
        throw ProjectExceptions.SSLContextException("No one SSL protocol from $SUPPORTED_SSL_PROTOCOLS is supported by this device")
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("unsafe") trustManager: X509TrustManager,
        sslContext: SSLContext,
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val builder = OkHttpClient.Builder()
            .connectTimeout(
                timeout = DEFAULT_CONNECTION_TIMEOUT_IN_SEC,
                unit = TimeUnit.SECONDS,
            )
            .readTimeout(
                timeout = DEFAULT_READ_TIMEOUT_IN_SEC,
                unit = TimeUnit.SECONDS,
            )
            .sslSocketFactory(
                sslSocketFactory = sslContext.socketFactory,
                trustManager = trustManager,
            )
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val origin = chain.request()

                val updatedRequest = origin.newBuilder()
                    .addHeader(
                        name = "X-RapidAPI-Key",
                        value = "7ab0517ebamsh354f040b6a7611bp168b39jsn458e440fa95f"
                    )
                    .addHeader(
                        name = "X-RapidAPI-Host",
                        value = "yahoo-finance15.p.rapidapi.com"
                    )
                    .build()

                chain.proceed(request = updatedRequest)
            }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        val gsonFactory = GsonConverterFactory.create(gson)

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_API_URL).client(httpClient)
            .addConverterFactory(gsonFactory).build()
    }

    @Provides
    @Singleton
    fun provideStocksApi(retrofit: Retrofit): StocksApi = retrofit.create(StocksApi::class.java)

}
