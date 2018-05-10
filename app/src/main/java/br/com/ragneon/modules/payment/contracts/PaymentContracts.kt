package br.com.ragneon.modules.payment.contracts

import br.com.ragneon.commons.BaseContracts
import br.com.ragneon.models.Contact
import br.com.ragneon.models.Transfer
import br.com.ragneon.models.User
import br.com.ragneon.network.requests.SendMoneyRequest
import br.com.ragneon.network.responses.TransferResponse
import io.reactivex.Completable
import io.reactivex.Single

interface PaymentContracts {

    interface View : BaseContracts.View {
        var presenter: Presenter
        fun showContacts(contacts: ArrayList<Contact>)
    }

    interface Presenter {
        fun onGetContacts()
        fun onGoToInputMoneyDialog(contact: Contact)
    }

    interface Router {
        fun showInputMoneyDialog(contact: Contact)
    }
}

