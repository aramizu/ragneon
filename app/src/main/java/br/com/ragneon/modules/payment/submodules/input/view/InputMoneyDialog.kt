package br.com.ragneon.modules.payment.submodules.input.view

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import br.com.ragneon.R
import br.com.ragneon.extensions.hideKeyboard
import br.com.ragneon.models.Contact
import br.com.ragneon.modules.payment.submodules.input.contracts.InputMoneyContracts
import br.com.ragneon.modules.payment.submodules.input.router.InputMoneyRouter
import br.com.ragneon.utils.CurrencyUtils
import br.com.ragneon.utils.MockUtils
import br.com.ragneon.widgets.LoadingButton
import br.com.ragneon.widgets.UserAvatarListLayout
import kotterknife.bindView

class InputMoneyDialog(private val activity: Activity) : Dialog(activity), InputMoneyContracts.View {

    override lateinit var presenter: InputMoneyContracts.Presenter

    private val viewCloseDialog: ImageView by bindView(R.id.view_close_dialog)
    private val viewUserProfileImage: UserAvatarListLayout by bindView(R.id.item_contact_view_profile_image)
    private val textViewUserName: TextView by bindView(R.id.item_contact_text_view_name)
    private val textViewUserPhoneNumber: TextView by bindView(R.id.item_contact_text_view_phone_number)
    private val editTextInputMoney: EditText by bindView(R.id.edit_text_input_money)
    private val textViewErrorMessage: TextView by bindView(R.id.text_view_error_massage)
    private val buttonSend: LoadingButton  by bindView(R.id.button_send)

    private lateinit var contact: Contact

    init {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.view_input_money_dialog)

        InputMoneyRouter.assembleModule(this)

        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setupListeners()

        presenter.onCreate()
    }

    private fun clearFields() {
        editTextInputMoney.setText("")
        editTextInputMoney.setSelection(editTextInputMoney.text.toString().length)
        editTextInputMoney.requestFocus()
        buttonSend.setButtonText(activity.getString(R.string.button_send_money))
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    fun setupInformation(contact: Contact) {
        clearFields()
        this.contact = contact
        viewUserProfileImage.setDefaultProfileName(contact.clientName)
        viewUserProfileImage.setImageUrl(MockUtils.getMockProfileImage(contact.clientId))
        textViewUserName.text = contact.clientName
        textViewUserPhoneNumber.text = contact.phoneNumber
    }

    override fun onDetachedFromWindow() {
        activity.hideKeyboard()
        super.onDetachedFromWindow()
    }

    private fun setupListeners() {
        viewCloseDialog.setOnClickListener {
            dismiss()
        }
        editTextInputMoney.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setInputBudgetError(false, null)
                editTextInputMoney.removeTextChangedListener(this)
                editTextInputMoney.setText(CurrencyUtils.format(s.toString()))
                editTextInputMoney.setSelection(editTextInputMoney.text.toString().length)
                editTextInputMoney.addTextChangedListener(this)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        buttonSend.setOnClickListener {
            presenter.onSendMoneyClick(
                    contact.clientId,
                    CurrencyUtils.getDouble(editTextInputMoney.text.toString())
            )
        }
    }

    fun showSendMoneyConfirmation() {
        textViewErrorMessage.text = activity.getString(R.string.warning_send_money_success)
        textViewErrorMessage.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent))
        textViewErrorMessage.visibility = View.VISIBLE
    }

    fun setInputBudgetError(isInvalid: Boolean, message: String?) {
        textViewErrorMessage.setTextColor(ContextCompat.getColor(activity, R.color.error_color))

        message?.let {
            textViewErrorMessage.text = message
        }

        textViewErrorMessage.visibility = if (isInvalid) View.VISIBLE else View.GONE
    }

    fun showLoading() {
        buttonSend.isLoading(true)
    }

    fun hideLoading() {
        buttonSend.isLoading(false)
    }
}
