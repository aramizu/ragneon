package br.com.ragneon.modules.payment.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.ragneon.R
import br.com.ragneon.commons.BaseActivity
import br.com.ragneon.enums.ToolbarType
import br.com.ragneon.extensions.hideKeyboard
import br.com.ragneon.models.Contact
import br.com.ragneon.modules.payment.adapters.ContactsListAdapter
import br.com.ragneon.modules.payment.adapters.OnContactClickListener
import br.com.ragneon.modules.payment.contracts.PaymentContracts
import br.com.ragneon.modules.payment.router.PaymentRouter
import kotterknife.bindView

class PaymentActivity : BaseActivity(), PaymentContracts.View, OnContactClickListener {

    override lateinit var presenter: PaymentContracts.Presenter

    private val recyclerViewContactsList: RecyclerView by bindView(R.id.recycler_view_contacts_list)

    private lateinit var contactsListAdapter: ContactsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        PaymentRouter.assembleModule(this)

        setToolbarTitle(R.string.activity_send_money_screen_title)
        setUpToolbar(ToolbarType.PRIMARY_TOOLBAR_BACK_BUTTON)
        setUpList()

        presenter.onGetContacts()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    private fun setUpList() {
        contactsListAdapter = ContactsListAdapter(this, this)

        recyclerViewContactsList.run {
            adapter = contactsListAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun showContacts(contacts: ArrayList<Contact>) {
        contactsListAdapter.addContacts(contacts)
    }

    override fun onContactClick(contact: Contact) {
        presenter.onGoToInputMoneyDialog(contact)
    }
}
