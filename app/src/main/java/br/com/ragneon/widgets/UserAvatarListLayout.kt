package br.com.ragneon.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.ragneon.R
import br.com.ragneon.utils.UIUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotterknife.bindView

class UserAvatarListLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    private var nameLettersSize: String? = null

    private val avatar: ImageView by bindView(R.id.avatar)
    private val loading: ProgressBar by bindView(R.id.loading)
    private val userName: TextView by bindView(R.id.default_profile_name)
    private var nameLetters = String()

    init {
        LayoutInflater.from(context).inflate(R.layout.user_avatar_list_layout, this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UserAvatarListLayoutAttributes, 0, 0)
        nameLettersSize = typedArray.getString(R.styleable.UserAvatarListLayoutAttributes_nameLettersSize)

        typedArray.recycle()

        nameLettersSize?.let {
            when (it) {
                "small" -> userName.textSize = UIUtils.dpFromPx(context, 40f)
                else -> userName.textSize = UIUtils.dpFromPx(context, 56f)
            }
        }
    }

    fun setImageUrl(url: Int) {

        Glide.with(context)
                .load(url)
                .thumbnail(1f)
                .crossFade()
                .error(R.drawable.ic_user_default)
                .listener(object : RequestListener<Int, GlideDrawable> {
                    override fun onException(e: Exception, model: Int, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                        loading.visibility = View.GONE
                        setImageEmptyState(true)
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable, model: Int, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        loading.visibility = View.GONE
                        setImageEmptyState(false)
                        return false
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(RoundedCornersTransformation(context, 200, 0))
                .into(avatar)
    }

    private fun setImageEmptyState(isEmpty: Boolean) {
        avatar.visibility = if (isEmpty) View.GONE else View.VISIBLE
        userName.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    fun setDefaultProfileName(clientName: String?) {
        nameLetters = String()
        clientName?.let {
            val nameSegments = it.split(" ")
            if (nameSegments.size > 1) {
                nameLetters += nameSegments[0][0]
                nameLetters += nameSegments[nameSegments.size - 1][0]
            } else if (nameSegments.size == 1) {
                nameLetters += nameSegments[0][0]
            }
        }
        userName.text = nameLetters
    }
}
