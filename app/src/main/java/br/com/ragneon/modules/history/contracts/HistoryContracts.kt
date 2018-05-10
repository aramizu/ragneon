package br.com.ragneon.modules.history.contracts

import br.com.ragneon.commons.BaseContracts
import br.com.ragneon.models.Transfer
import br.com.ragneon.models.User
import br.com.ragneon.network.responses.TransferResponse
import io.reactivex.Single

interface HistoryContracts {

    interface RemoteDataManager {
        fun getTransfers(): Single<ArrayList<Transfer>>
    }

    interface View : BaseContracts.View {
        var presenter: Presenter
        fun showTransfers(transfers: ArrayList<Transfer>)
        fun showGroupedTransfers(transfers: ArrayList<Transfer>)
    }

    interface Presenter {
        fun onGetTransfers()
    }

    interface Interactor {
        fun getTransfers(): Single<ArrayList<Transfer>>
    }
}

