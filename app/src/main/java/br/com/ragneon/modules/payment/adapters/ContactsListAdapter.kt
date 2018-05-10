package br.com.ragneon.modules.payment.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.ragneon.R
import br.com.ragneon.models.Contact
import br.com.ragneon.utils.MockUtils
import br.com.ragneon.widgets.UserAvatarListLayout
import kotterknife.bindView

class ContactsListAdapter(
        var context: Context,
        private val listener: OnContactClickListener
) : RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder>() {

    private var contacts = ArrayList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val contactItem = LayoutInflater.from(context).inflate(R.layout.item_contact_list, parent, false)
        return ContactViewHolder(contactItem, listener)
    }

    override fun onBindViewHolder(viewHolder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        viewHolder.setupInformation(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun addContacts(contacts: MutableList<Contact>) {
        this.contacts.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View, private val listener: OnContactClickListener) : RecyclerView.ViewHolder(itemView) {
        private val itemContactViewContainer: RelativeLayout by bindView(R.id.item_contact_view_container)
        private val itemContactProfileImage: UserAvatarListLayout by bindView(R.id.item_contact_view_profile_image)
        private val itemContactName: TextView by bindView(R.id.item_contact_text_view_name)
        private val itemContactPhoneNumber: TextView by bindView(R.id.item_contact_text_view_phone_number)

        private lateinit var contact: Contact

        init {
            setupListeners()
        }

        fun setupInformation(contact: Contact) {
            this.contact = contact
            itemContactProfileImage.setDefaultProfileName(contact.clientName)
            itemContactProfileImage.setImageUrl(MockUtils.getMockProfileImage(contact.clientId))
            itemContactName.text = contact.clientName
            itemContactPhoneNumber.text = contact.phoneNumber
        }

        private fun setupListeners() {
            itemContactViewContainer.setOnClickListener {
                listener.onContactClick(contact)
            }
        }
    }
}
