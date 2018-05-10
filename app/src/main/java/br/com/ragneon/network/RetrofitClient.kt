package br.com.ragneon.network

import android.annotation.SuppressLint
import br.com.ragneon.BuildConfig
import br.com.ragneon.database.realm.RealmDataManager
import br.com.ragneon.network.constants.RetrofitConstants
import br.com.ragneon.network.interceptors.LoggingInterceptor
import br.com.ragneon.network.interceptors.NetworkStatusInterceptor
import br.com.ragneon.network.interceptors.UserAuthInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getInstance(): Retrofit {

            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                    .addInterceptor(NetworkStatusInterceptor())
                    .addInterceptor(UserAuthInterceptor())
                    .addNetworkInterceptor(LoggingInterceptor().interceptor)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)

            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            mapper.dateFormat = SimpleDateFormat(RetrofitConstants.RETROFIT_DATE_FORMAT)

            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create(mapper))
                    .baseUrl(BuildConfig.BASE_HOST)
                    .client(builder.build())
                    .build()
        }
    }
}
