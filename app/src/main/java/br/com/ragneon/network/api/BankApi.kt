package br.com.ragneon.network.api

import br.com.ragneon.network.requests.SendMoneyRequest
import br.com.ragneon.network.responses.TransferResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Retrofit service methods
 */
interface BankApi {

    /**
     * Transfers
     */
    @GET("/GetTransfers")
    fun getTransfers(@Query("token") token: String?): Single<List<TransferResponse>>

    /**
     * Send Money
     */
    @POST("/SendMoney")
    fun sendMoney(@Body sendMoneyRequest: SendMoneyRequest): Completable
}
