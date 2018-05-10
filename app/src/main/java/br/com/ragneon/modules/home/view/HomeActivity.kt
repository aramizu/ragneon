package br.com.ragneon.modules.home.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import br.com.ragneon.R
import br.com.ragneon.commons.BaseActivity
import br.com.ragneon.enums.ToolbarType
import br.com.ragneon.models.User
import br.com.ragneon.modules.home.contracts.HomeContracts
import br.com.ragneon.modules.home.router.HomeRouter
import br.com.ragneon.widgets.UserAvatarLayout
import kotterknife.bindView

class HomeActivity : BaseActivity(), HomeContracts.View {

    private val viewUserAvatar: UserAvatarLayout by bindView(R.id.view_user_avatar)
    private val textViewUserName: TextView by bindView(R.id.text_view_user_name)
    private val textViewUserEmail: TextView by bindView(R.id.text_view_user_email)
    private val buttonSendMoney: Button by bindView(R.id.button_send_money)
    private val buttonHistory: Button by bindView(R.id.button_history)

    override lateinit var presenter: HomeContracts.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        HomeRouter.assembleModule(this)

        setUpToolbar(ToolbarType.NO_TOOLBAR)
        setButtonsListeners()

        presenter.onGetCredentials()
    }

    private fun setButtonsListeners() {
        buttonSendMoney.setOnClickListener {
            presenter.onSendMoneyClick()
        }
        buttonHistory.setOnClickListener {
            presenter.onHistoryClick()
        }
    }

    override fun showUserInformation(user: User) {
        textViewUserName.text = user.userName
        textViewUserEmail.text = user.email
        viewUserAvatar.setImageUrl(user.profileImage)
    }
}
