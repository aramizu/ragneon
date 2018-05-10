package br.com.ragneon.modules.history.view

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import br.com.ragneon.R
import br.com.ragneon.commons.BaseActivity
import br.com.ragneon.enums.ToolbarType
import br.com.ragneon.models.Transfer
import br.com.ragneon.modules.history.adapters.TransfersAmountValueListAdapter
import br.com.ragneon.modules.history.adapters.TransfersListAdapter
import br.com.ragneon.modules.history.contracts.HistoryContracts
import br.com.ragneon.modules.history.router.HistoryRouter
import kotterknife.bindView

class HistoryActivity : BaseActivity(), HistoryContracts.View {

    override lateinit var presenter: HistoryContracts.Presenter

    private val recyclerViewHistoryList: RecyclerView by bindView(R.id.recycler_view_history_list)
    private val recyclerViewTransferAmountList: RecyclerView by bindView(R.id.recycler_view_transfer_amount_list)
    private lateinit var transfersAmountValueListAdapter: TransfersAmountValueListAdapter

    private lateinit var transfersListAdapter: TransfersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        HistoryRouter.assembleModule(this)

        setToolbarTitle(R.string.activity_history_screen_title)
        setUpToolbar(ToolbarType.PRIMARY_TOOLBAR_BACK_BUTTON)
        setUpList()

        presenter.onGetTransfers()
    }

    private fun setUpList() {
        transfersListAdapter = TransfersListAdapter(this)

        recyclerViewHistoryList.run {
            adapter = transfersListAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
        }

        transfersAmountValueListAdapter = TransfersAmountValueListAdapter(this)

        recyclerViewTransferAmountList.run {
            visibility = View.VISIBLE
            adapter = transfersAmountValueListAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun showGroupedTransfers(transfers: ArrayList<Transfer>) {
        transfersAmountValueListAdapter.addTransfers(transfers)
    }

    override fun showTransfers(transfers: ArrayList<Transfer>) {
        transfersListAdapter.addTransfer(transfers)
    }
}
