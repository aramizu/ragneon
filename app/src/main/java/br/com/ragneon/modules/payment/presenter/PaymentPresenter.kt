package br.com.ragneon.modules.payment.presenter

import br.com.ragneon.config.AndroidApplication
import br.com.ragneon.models.Contact
import br.com.ragneon.modules.payment.contracts.PaymentContracts
import br.com.ragneon.modules.payment.router.PaymentRouter
import br.com.ragneon.modules.payment.view.PaymentActivity
import br.com.ragneon.utils.MockUtils
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class PaymentPresenter(private val view: PaymentActivity,
                       private val router: PaymentRouter) : PaymentContracts.Presenter {

    override fun onGoToInputMoneyDialog(contact: Contact) {
        router.showInputMoneyDialog(contact)
    }

    override fun onGetContacts() {
        val contacts: List<Contact> =
                ObjectMapper().readValue(
                        MockUtils.loadJSONFromAsset(AndroidApplication.applicationContext(), "contacts.json"),
                        object : TypeReference<List<Contact>>() {})
        view.showContacts(contacts as ArrayList<Contact>)
    }
}