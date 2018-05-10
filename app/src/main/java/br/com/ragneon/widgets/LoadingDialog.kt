package br.com.ragneon.widgets

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import br.com.ragneon.R

class LoadingDialog(private var activity: Activity) : Dialog(activity) {

    init {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.view_loading_dialog)

        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun showDialog() {
        if (!activity.isFinishing && !isShowing) {
            show()
        }
    }

    fun hideDialog() {
        if (!activity.isFinishing) {
            dismiss()
        }
    }
}
