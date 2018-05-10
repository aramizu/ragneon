package br.com.ragneon.commons

import android.content.Context
import android.support.annotation.StringRes
import br.com.ragneon.enums.ToolbarType

interface BaseContracts {

    interface View {
        fun showDialog(@StringRes resTitleId: Int, @StringRes resMessageId: Int)
        fun showDialog(title: String?, message: String)
        fun getContext(): Context
        fun getBaseActivity(): BaseActivity?
        fun setUpToolbar(style: ToolbarType)
        fun setToolbarTitle(@StringRes resId: Int)
        fun setToolbarTitle(message: String)
        fun showLoading()
        fun hideLoading()
    }
}
