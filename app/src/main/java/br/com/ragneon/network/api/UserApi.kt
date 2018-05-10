package br.com.ragneon.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service methods
 */
interface UserApi {

    /**
     * Credentials
     */
    @GET("/GenerateToken")
    fun getCredentials(@Query("nome") name: String = "Rafael Aramizu Gomes",
                       @Query("email") email: String = "rafael.aramizu@gmail.com.br"): Single<String>
}
