package br.com.ragneon.modules.history.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import br.com.ragneon.R
import br.com.ragneon.models.Transfer
import br.com.ragneon.utils.CurrencyUtils
import br.com.ragneon.utils.MockUtils
import br.com.ragneon.widgets.UserAvatarListLayout
import kotterknife.bindView
import android.widget.TableLayout
import br.com.ragneon.utils.UIUtils


class TransfersAmountValueListAdapter(
        private var context: Context
) : RecyclerView.Adapter<TransfersAmountValueListAdapter.TransferAmountValueViewHolder>() {

    private var transfersAmountValueList: List<Transfer> = ArrayList()

    private var proportionBase: Double = 1.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferAmountValueViewHolder {
        val transferItem = LayoutInflater.from(context).inflate(R.layout.item_transfer_user_amount, parent, false)
        return TransferAmountValueViewHolder(transferItem)
    }

    override fun onBindViewHolder(viewHolder: TransferAmountValueViewHolder, position: Int) {
        val transferAmountValue = transfersAmountValueList[position]
        viewHolder.setInformation(transferAmountValue)
    }

    override fun getItemCount(): Int {
        return transfersAmountValueList.size
    }

    fun addTransfers(transfers: List<Transfer>) {
        this.proportionBase = transfers[0].Valor
        this.transfersAmountValueList = transfers
        notifyDataSetChanged()
    }

    inner class TransferAmountValueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewTransferAmount: TextView by bindView(R.id.text_view_transfer_amount)
        private val viewUserProfileImage: UserAvatarListLayout by bindView(R.id.item_transfer_view_profile_image)
        private val viewChartHeight: View by bindView(R.id.view_chart_height)
        private val viewDiffSpace: View by bindView(R.id.view_diff_space)

        fun setInformation(transfer: Transfer) {
            val weight = (transfer.Valor / proportionBase).toFloat()

            viewDiffSpace.layoutParams = LinearLayout.LayoutParams(UIUtils.pxFromDp(context, 1f).toInt(), 0, 1 - weight)

            val layoutParams = LinearLayout.LayoutParams(UIUtils.pxFromDp(context, 3f).toInt(), 0, weight)
            layoutParams.gravity = Gravity.CENTER
            viewChartHeight.layoutParams = layoutParams

            viewUserProfileImage.setDefaultProfileName(MockUtils.getMockContactName(transfer.ClienteId))
            viewUserProfileImage.setImageUrl(MockUtils.getMockProfileImage(transfer.ClienteId))
            textViewTransferAmount.text = CurrencyUtils.format(transfer.Valor, true)
        }
    }
}
