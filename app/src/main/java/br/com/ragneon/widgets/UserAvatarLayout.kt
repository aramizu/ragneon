package br.com.ragneon.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import br.com.ragneon.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotterknife.bindView

class UserAvatarLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    private val avatar: ImageView by bindView(R.id.avatar)
    private val loading: ProgressBar by bindView(R.id.loading)

    init {
        LayoutInflater.from(context).inflate(R.layout.user_avatar_layout, this, true)
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
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable, model: Int, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        loading.visibility = View.GONE
                        return false
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(RoundedCornersTransformation(context, 200, 0))
                .into(avatar)
    }
}
