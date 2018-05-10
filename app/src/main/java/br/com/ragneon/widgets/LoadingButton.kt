package br.com.ragneon.widgets

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import br.com.ragneon.R
import kotterknife.bindView

class LoadingButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    val loading: ProgressBar by bindView(R.id.loading)
    val button: Button by bindView(R.id.button)
    private var buttonText = String()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading_button, this, true)
    }

    fun setButtonText(text: String) {
        buttonText = text
        button.text = text
    }

    override fun setOnClickListener(listener: OnClickListener) {
        button.setOnClickListener(listener)
    }

    fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            button.text = String()
            loading.visibility = View.VISIBLE
        } else {
            button.text = buttonText
            loading.visibility = View.GONE
        }
        button.isEnabled = !isLoading
    }
}