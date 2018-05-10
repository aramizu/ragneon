package br.com.ragneon.modules.history.interactor

import br.com.ragneon.models.Transfer
import br.com.ragneon.models.User
import br.com.ragneon.modules.history.contracts.HistoryContracts
import br.com.ragneon.modules.history.datamanager.HistoryRemoteDataManager
import br.com.ragneon.modules.home.contracts.HomeContracts
import br.com.ragneon.modules.home.datamanager.HomeRemoteDataManager
import io.reactivex.Single

class HistoryInteractor(
        private val historyRemoteDataManager: HistoryContracts.RemoteDataManager = HistoryRemoteDataManager()
) : HistoryContracts.Interactor {

    override fun getTransfers(): Single<ArrayList<Transfer>> {
        return historyRemoteDataManager.getTransfers()
    }
}
