package br.com.ragneon.modules.payment.submodules.input.router

import android.content.Context
import br.com.ragneon.models.Contact
import br.com.ragneon.modules.payment.submodules.input.datamanager.InputMoneyRemoteDataManager
import br.com.ragneon.modules.payment.submodules.input.interactor.InputMoneyInteractor
import br.com.ragneon.modules.payment.submodules.input.presenter.InputMoneyPresenter
import br.com.ragneon.modules.payment.submodules.input.view.InputMoneyDialog

class InputMoneyRouter(val context: Context) {

    companion object {

        private lateinit var contact: Contact

        fun assembleModule(view: InputMoneyDialog) {
            val remoteDataManager = InputMoneyRemoteDataManager()
            val interactor = InputMoneyInteractor(remoteDataManager)
            val presenter = InputMoneyPresenter(view, interactor, contact)
            view.presenter = presenter
        }

        fun assembleModuleData(contact: Contact) {
            this.contact = contact
        }
    }
}