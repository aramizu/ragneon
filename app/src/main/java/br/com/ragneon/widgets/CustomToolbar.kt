package br.com.ragneon.widgets

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import br.com.ragneon.R
import kotterknife.bindView

class CustomToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    val toolbarContainer: LinearLayout by bindView(R.id.custom_toolbar_container)
    val appbarlayout: AppBarLayout by bindView(R.id.custom_toolbar_appbarlayout)
    val toolbar: Toolbar by bindView(R.id.custom_toolbar)
    val toolbarTitle: TextView by bindView(R.id.custom_toolbar_text_view_title)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_toolbar, this, true)
    }

    fun setToolbarTitle(title: String) {
        toolbarTitle.text = title
    }
}