package ey.samarin.providers.network

import android.content.Context
import ey.samarin.exceptions.ProjectExceptions
import java.io.BufferedInputStream
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory

class AssetsCertificateStore(context: Context) {

    val keyStore: KeyStore

    companion object {
        //Use folder with this name to save required certificates if necessary
        private const val CERTIFICATES_ASSET_FOLDER_NAME = "certificate"
    }

    init {
        val keyStoreType = KeyStore.getDefaultType()
        keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        val certificates = context.assets.list(CERTIFICATES_ASSET_FOLDER_NAME) ?: emptyArray()
        for (index in certificates.indices) {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val caInput: InputStream = BufferedInputStream(
                context.assets.open(CERTIFICATES_ASSET_FOLDER_NAME + "/" + certificates[index])
            )
            lateinit var certificate: Certificate
            caInput.use { inputStream ->
                certificate = certificateFactory.generateCertificate(inputStream)
            }

            // Create a KeyStore containing our trusted CAs
            keyStore.setCertificateEntry("ca$index", certificate)
        }

        // copy certs from android AndroidCAStore to this store [added for the future]
        val defaultCAs = KeyStore.getInstance("AndroidCAStore")
        if (defaultCAs != null) {
            defaultCAs.load(null, null)
            val keyAliases = defaultCAs.aliases()
            while (keyAliases.hasMoreElements()) {
                val alias = keyAliases.nextElement()
                val cert = defaultCAs.getCertificate(alias)
                try {
                    if (!keyStore.containsAlias(alias)) {
                        keyStore.setCertificateEntry(alias, cert)
                    }
                } catch (exception: Exception) {
                    throw ProjectExceptions.CertificateSetException(
                        message = "Error adding certificate cause: ${exception.message}",
                    )
                }
            }
        }
    }
}