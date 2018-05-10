package br.com.ragneon.modules.history.presenter

import br.com.ragneon.extensions.getErrorMessage
import br.com.ragneon.models.Transfer
import br.com.ragneon.modules.history.contracts.HistoryContracts
import br.com.ragneon.modules.history.interactor.HistoryInteractor
import br.com.ragneon.modules.history.view.HistoryActivity
import com.kizitonwose.android.disposebag.DisposeBag
import com.kizitonwose.android.disposebag.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HistoryPresenter(private val view: HistoryActivity,
                       private val interactor: HistoryContracts.Interactor = HistoryInteractor(),
                       private val disposeBag: DisposeBag) : HistoryContracts.Presenter {

    override fun onGetTransfers() {
        view.showLoading()
        interactor.getTransfers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { transfers ->
                            view.hideLoading()
                            view.showTransfers(transfers)
                            view.showGroupedTransfers(groupTransfersByValue(transfers))
                        },
                        onError = { error ->
                            view.hideLoading()
                            view.showDialog(null, error.getErrorMessage())
                        }
                ).disposedBy(disposeBag)
    }

    private fun groupTransfersByValue(transfers: ArrayList<Transfer>): ArrayList<Transfer> {
        val groupedTransfers = ArrayList<Transfer>()
        val byClientId = transfers.groupBy { it.ClienteId }
        val bySumValue = byClientId.map { it.value.sumByDouble { it.Valor } }

        var index = 0
        byClientId.forEach {
            groupedTransfers.add(Transfer(it.value[0].ClienteId, bySumValue[index]))
            index++
        }

        return ArrayList(groupedTransfers.sortedByDescending { it.Valor })
    }
}