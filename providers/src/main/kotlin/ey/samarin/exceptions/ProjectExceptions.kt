package ey.samarin.exceptions


// TODO [202311308]: Move this class to Core module
sealed class ProjectExceptions(message: String = "") : Exception(message) {
    data class SSLContextException(override val message: String) : ProjectExceptions(message)
    data class CertificateSetException(override val message: String) : ProjectExceptions(message)
}