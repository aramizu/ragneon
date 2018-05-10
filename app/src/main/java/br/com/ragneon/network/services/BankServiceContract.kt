package br.com.ragneon.network.services

import br.com.ragneon.network.requests.SendMoneyRequest
import br.com.ragneon.network.responses.TransferResponse
import io.reactivex.Completable
import io.reactivex.Single

interface BankServiceContract {
    fun getTransfers(token: String?): Single<List<TransferResponse>>
    fun sendMoney(sendMoneyRequest: SendMoneyRequest): Completable
}