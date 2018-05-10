package br.com.ragneon.modules.payment.router

import br.com.ragneon.models.Contact
import br.com.ragneon.modules.payment.submodules.input.router.InputMoneyRouter
import br.com.ragneon.modules.payment.submodules.input.view.InputMoneyDialog
import br.com.ragneon.modules.payment.contracts.PaymentContracts
import br.com.ragneon.modules.payment.presenter.PaymentPresenter
import br.com.ragneon.modules.payment.view.PaymentActivity

class PaymentRouter(private val activity: PaymentActivity) : PaymentContracts.Router {

    override fun showInputMoneyDialog(contact: Contact) {
        InputMoneyRouter.assembleModuleData(contact)
        InputMoneyDialog(activity).show()
    }

    companion object {

        fun assembleModule(view: PaymentActivity) {
            val router = PaymentRouter(view)
            val presenter = PaymentPresenter(view, router)
            view.presenter = presenter
        }
    }
}