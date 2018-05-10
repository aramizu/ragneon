package br.com.ragneon.modules.payment.submodules.input.interactor

import br.com.ragneon.errors.InputMoneyError
import br.com.ragneon.modules.payment.submodules.input.contracts.InputMoneyContracts
import br.com.ragneon.modules.payment.submodules.input.datamanager.InputMoneyRemoteDataManager
import io.reactivex.Completable

class InputMoneyInteractor(
        private val inputMoneyRemoteDataManager: InputMoneyContracts.RemoteDataManager = InputMoneyRemoteDataManager()
) : InputMoneyContracts.Interactor {

    override fun sendMoney(clientId: Long, money: Double): Completable {
        // Business Rules
        if (money <= 0) {
            return Completable.error( InputMoneyError() )
        }
        return inputMoneyRemoteDataManager.sendMoney(clientId, money)
    }
}
