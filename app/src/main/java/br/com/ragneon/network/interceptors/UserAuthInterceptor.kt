package br.com.ragneon.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class UserAuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val definitiveRequest = originalRequest.newBuilder().addHeader("Content-Type", "application/json")
        return chain.proceed(definitiveRequest.build())
    }
}