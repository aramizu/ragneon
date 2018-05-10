package br.com.ragneon.modules.payment.submodules.input.presenter

import br.com.ragneon.R
import br.com.ragneon.config.AndroidApplication
import br.com.ragneon.errors.InputMoneyError
import br.com.ragneon.errors.NetworkConnectionError
import br.com.ragneon.models.Contact
import br.com.ragneon.modules.payment.submodules.input.contracts.InputMoneyContracts
import br.com.ragneon.modules.payment.submodules.input.interactor.InputMoneyInteractor
import br.com.ragneon.modules.payment.submodules.input.view.InputMoneyDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class InputMoneyPresenter(private val view: InputMoneyDialog,
                          private val interactor: InputMoneyContracts.Interactor = InputMoneyInteractor(),
                          private val contact: Contact) : InputMoneyContracts.Presenter {

    override fun onCreate() {
        view.setupInformation(contact)
    }

    override fun onSendMoneyClick(clientId: Long, money: Double) {
        view.showLoading()
        interactor.sendMoney(clientId, money)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onComplete = {
                            view.hideLoading()
                            view.showSendMoneyConfirmation()
                        },
                        onError = { error ->
                            view.hideLoading()

                            when (error) {
                                is InputMoneyError -> view.setInputBudgetError(
                                        true,
                                        AndroidApplication.applicationContext().getString(R.string.error_input_budget_message))
                                is NetworkConnectionError -> view.setInputBudgetError(
                                        true,
                                        AndroidApplication.applicationContext().getString(R.string.error_no_connection_message)
                                )
                            }
                        }
                )
    }
}