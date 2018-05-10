package br.com.ragneon.modules.payment.submodules.input.datamanager

import br.com.ragneon.database.realm.RealmDataManager
import br.com.ragneon.database.realm.RealmDataManagerContract
import br.com.ragneon.modules.payment.submodules.input.contracts.InputMoneyContracts
import br.com.ragneon.network.requests.SendMoneyRequest
import br.com.ragneon.network.services.BankService
import br.com.ragneon.network.services.BankServiceContract
import io.reactivex.Completable

class InputMoneyRemoteDataManager(
        private val bankService: BankServiceContract = BankService(),
        private val realmDataManager: RealmDataManagerContract = RealmDataManager()
) : InputMoneyContracts.RemoteDataManager {

    override fun sendMoney(clientId: Long, money: Double): Completable {
        val token = realmDataManager.retrieveUserSession().token ?: String()
        val sendMoneyRequest = SendMoneyRequest(clientId, token, money)

        return bankService.sendMoney(sendMoneyRequest)
    }
}