package br.com.ragneon.network.interceptors

import br.com.ragneon.config.AndroidApplication
import br.com.ragneon.errors.NetworkConnectionError
import br.com.ragneon.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val context = AndroidApplication.applicationContext()
        val isNetworkConnected = NetworkUtils.isNetworkConnected(context)
        return if (isNetworkConnected) chain.proceed(chain.request()) else throw NetworkConnectionError()
    }
}