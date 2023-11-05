package ey.samarin.exceptions


sealed class ProjectExceptions(message: String = "") : Exception(message) {
    data class SSLContextException(override val message: String) : ProjectExceptions(message)
    data class CertificateSetException(override val message: String) : ProjectExceptions(message)
}