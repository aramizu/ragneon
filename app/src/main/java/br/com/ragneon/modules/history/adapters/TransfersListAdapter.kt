package br.com.ragneon.modules.history.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.ragneon.R
import br.com.ragneon.models.Transfer
import br.com.ragneon.utils.CurrencyUtils
import br.com.ragneon.utils.MockUtils
import br.com.ragneon.widgets.UserAvatarListLayout
import kotterknife.bindView

class TransfersListAdapter(var context: Context) : RecyclerView.Adapter<TransfersListAdapter.HistoryViewHolder>() {

    private var transfers = ArrayList<Transfer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val transferItem = LayoutInflater.from(context).inflate(R.layout.item_transfer_list, parent, false)
        return HistoryViewHolder(transferItem)
    }

    override fun onBindViewHolder(viewHolder: HistoryViewHolder, position: Int) {
        val transfer = transfers[position]
        viewHolder.setupInformation(transfer)
    }

    override fun getItemCount(): Int {
        return transfers.size
    }

    fun addTransfer(contacts: MutableList<Transfer>) {
        this.transfers.addAll(contacts)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemTransferProfileImage: UserAvatarListLayout by bindView(R.id.item_transfer_view_profile_image)
        private val itemTransferName: TextView by bindView(R.id.item_transfer_text_view_name)
        private val itemTransferPhoneNumber: TextView by bindView(R.id.item_transfer_text_view_phone_number)
        private val itemTransferValue: TextView by bindView(R.id.item_transfer_text_view_value)

        fun setupInformation(transfer: Transfer) {
            itemTransferProfileImage.setDefaultProfileName(MockUtils.getMockContactName(transfer.ClienteId))
            itemTransferProfileImage.setImageUrl(MockUtils.getMockProfileImage(transfer.ClienteId))
            itemTransferName.text = MockUtils.getMockContactName(transfer.ClienteId)
            itemTransferPhoneNumber.text = MockUtils.getMockContactPhoneNumber(transfer.ClienteId)
            itemTransferValue.text = CurrencyUtils.format(transfer.Valor)
        }
    }
}
