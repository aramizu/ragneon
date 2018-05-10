package br.com.ragneon.modules.home.router

import android.content.Context
import br.com.ragneon.modules.history.view.HistoryActivity
import br.com.ragneon.modules.home.contracts.HomeContracts
import br.com.ragneon.modules.home.datamanager.HomeRemoteDataManager
import br.com.ragneon.modules.home.interactor.HomeInteractor
import br.com.ragneon.modules.home.presenter.HomePresenter
import br.com.ragneon.modules.home.view.HomeActivity
import br.com.ragneon.modules.payment.view.PaymentActivity
import com.kizitonwose.android.disposebag.DisposeBag
import org.jetbrains.anko.startActivity

class HomeRouter(val context: Context) : HomeContracts.Router {

    override fun goToPaymentModule() {
        context.startActivity<PaymentActivity>()
    }

    override fun goToHistoryModule() {
        context.startActivity<HistoryActivity>()
    }

    companion object {

        fun assembleModule(view: HomeActivity) {
            val router = HomeRouter(view)
            val remoteDataManager = HomeRemoteDataManager()
            val interactor = HomeInteractor(remoteDataManager)
            val presenter = HomePresenter(view, interactor, router, DisposeBag(view))
            view.presenter = presenter
        }
    }
}