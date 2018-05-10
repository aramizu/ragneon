package br.com.ragneon.network.services

import br.com.ragneon.extensions.getBankApi
import br.com.ragneon.network.RetrofitClient
import br.com.ragneon.network.requests.SendMoneyRequest
import br.com.ragneon.network.responses.TransferResponse
import io.reactivex.Completable
import io.reactivex.Single

class BankService : BankServiceContract {

    override fun getTransfers(token: String?): Single<List<TransferResponse>> {
        return RetrofitClient.getBankApi().getTransfers(token)
    }

    override fun sendMoney(sendMoneyRequest: SendMoneyRequest): Completable {
        return RetrofitClient.getBankApi().sendMoney(sendMoneyRequest)
    }
}
