package br.com.ragneon.widgets

import br.com.ragneon.models.Contact

interface OnSendMoneyClickListener {
    fun onSendMoneyClick(contact: Contact, money: Double)
}