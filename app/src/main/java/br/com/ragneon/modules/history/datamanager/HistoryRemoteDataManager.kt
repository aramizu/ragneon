package br.com.ragneon.modules.history.datamanager

import br.com.ragneon.config.AndroidApplication
import br.com.ragneon.database.realm.RealmDataManager
import br.com.ragneon.database.realm.RealmDataManagerContract
import br.com.ragneon.models.Contact
import br.com.ragneon.models.Transfer
import br.com.ragneon.modules.history.contracts.HistoryContracts
import br.com.ragneon.network.responses.TransferResponse
import br.com.ragneon.network.services.BankService
import br.com.ragneon.network.services.BankServiceContract
import br.com.ragneon.utils.MockUtils
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.reactivex.Single

class HistoryRemoteDataManager(
        private val bankService: BankServiceContract = BankService(),
        private val realmDataManager: RealmDataManagerContract = RealmDataManager()
) : HistoryContracts.RemoteDataManager {

    override fun getTransfers(): Single<ArrayList<Transfer>> {
        /*
        return bankService.getTransfers(realmDataManager.retrieveUserSession().token)
                .map { transfers -> transfers.map { Transfer(it) } as ArrayList<Transfer> }
                */

        val transfers: ArrayList<TransferResponse> =
                ObjectMapper().readValue(
                        MockUtils.loadJSONFromAsset(AndroidApplication.applicationContext(), "transfers.json"),
                        object : TypeReference<List<TransferResponse>>() {})

        return Single.just(transfers.map { Transfer(it) } as ArrayList<Transfer>)
    }
}