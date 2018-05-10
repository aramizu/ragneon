package br.com.ragneon.errors

open class BaseThrowableError: Throwable() {
    var errorMessage: String? = null
}