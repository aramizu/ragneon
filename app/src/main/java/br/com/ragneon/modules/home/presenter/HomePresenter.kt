package br.com.ragneon.modules.home.presenter

import br.com.ragneon.extensions.getErrorMessage
import br.com.ragneon.modules.home.contracts.HomeContracts
import br.com.ragneon.modules.home.interactor.HomeInteractor
import br.com.ragneon.modules.home.router.HomeRouter
import br.com.ragneon.modules.home.view.HomeActivity
import com.kizitonwose.android.disposebag.DisposeBag
import com.kizitonwose.android.disposebag.disposedBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeActivity,
                    private val interactor: HomeContracts.Interactor = HomeInteractor(),
                    private val router: HomeRouter,
                    private val disposeBag: DisposeBag) : HomeContracts.Presenter {

    override fun onGetCredentials() {
        view.showLoading()
        interactor.getCredentials()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { user ->
                            view.hideLoading()
                            view.showUserInformation(user)
                        },
                        onError = { error ->
                            view.hideLoading()
                            view.showDialog(null, error.getErrorMessage())
                        }
                ).disposedBy(disposeBag)
    }

    override fun onSendMoneyClick() {
        router.goToPaymentModule()
    }

    override fun onHistoryClick() {
        router.goToHistoryModule()
    }
}