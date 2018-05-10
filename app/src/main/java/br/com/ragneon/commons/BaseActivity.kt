package br.com.ragneon.commons

import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import br.com.ragneon.R
import br.com.ragneon.enums.ToolbarType
import br.com.ragneon.utils.NetworkUtils
import br.com.ragneon.widgets.CustomToolbar
import br.com.ragneon.widgets.LoadingDialog
import kotterknife.bindOptionalView
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

abstract class BaseActivity : AppCompatActivity(), BaseContracts.View {

    private val widgetCustomToolbar: CustomToolbar? by bindOptionalView(R.id.activity_main_widget_toolbar)
    private var loadingDialog: LoadingDialog? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        loadingDialog = LoadingDialog(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun getContext(): Context {
        return this
    }

    override fun getBaseActivity(): BaseActivity? {
        return this
    }

    override fun setUpToolbar(style: ToolbarType) {
        widgetCustomToolbar?.let {
            setSupportActionBar(widgetCustomToolbar?.toolbar)
        }
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)

            when (style) {
                ToolbarType.NO_TOOLBAR -> widgetCustomToolbar?.visibility = View.GONE
                ToolbarType.PRIMARY_TOOLBAR_NO_HOME_BUTTON -> {
                    it.setHomeButtonEnabled(false)
                    it.setDisplayHomeAsUpEnabled(false)
                    it.setHomeAsUpIndicator(null)
                }
                ToolbarType.PRIMARY_TOOLBAR_BACK_BUTTON -> {
                    it.setHomeButtonEnabled(true)
                    it.setDisplayHomeAsUpEnabled(true)
                    it.setHomeAsUpIndicator(getDrawable(R.drawable.ic_back))
                }
            }
        }
    }

    override fun setToolbarTitle(@StringRes resId: Int) {
        setToolbarTitle(getString(resId))
    }

    override fun setToolbarTitle(message: String) {
        widgetCustomToolbar?.setToolbarTitle(message)
    }

    override fun showLoading() {
        loadingDialog?.showDialog()
    }

    override fun hideLoading() {
        loadingDialog?.hideDialog()
    }

    override fun showDialog(@StringRes resTitleId: Int, @StringRes resMessageId: Int) {
        showDialog(getString(resTitleId), getString(resMessageId))
    }

    override fun showDialog(title: String?, message: String) {
        alert(message, title) {
            yesButton {
                it.dismiss()
            }
        }.show()
    }
}
