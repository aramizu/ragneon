package br.com.ragneon.modules.history.router

import android.content.Context
import br.com.ragneon.modules.history.datamanager.HistoryRemoteDataManager
import br.com.ragneon.modules.history.interactor.HistoryInteractor
import br.com.ragneon.modules.history.presenter.HistoryPresenter
import br.com.ragneon.modules.history.view.HistoryActivity
import com.kizitonwose.android.disposebag.DisposeBag

class HistoryRouter(val context: Context) {

    companion object {

        fun assembleModule(view: HistoryActivity) {
            val remoteDataManager = HistoryRemoteDataManager()
            val interactor = HistoryInteractor(remoteDataManager)
            val presenter = HistoryPresenter(view, interactor, DisposeBag(view))
            view.presenter = presenter
        }
    }
}