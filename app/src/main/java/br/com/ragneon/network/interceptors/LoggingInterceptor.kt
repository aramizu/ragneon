package br.com.ragneon.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor(level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY) {

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        interceptor.level = level
    }
}