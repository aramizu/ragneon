package br.com.ragneon.modules.payment.adapters

import br.com.ragneon.models.Contact

interface OnContactClickListener {
    fun onContactClick(contact: Contact)
}