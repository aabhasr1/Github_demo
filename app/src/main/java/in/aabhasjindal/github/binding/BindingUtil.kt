package `in`.aabhasjindal.github.binding

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.ui.base.BaseRecyclerAdapter
import `in`.aabhasjindal.github.ui.base.BaseViewModel
import `in`.aabhasjindal.github.ui.base.GenericAdapter
import `in`.aabhasjindal.github.ui.base.RecyclerConfiguration
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingUtil {
    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("url")
    @JvmStatic
    fun setUrl(webView: WebView, url: String?) {
        url?.let { urlString ->
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(urlString)
            webView.isHorizontalScrollBarEnabled = false
            val webSettings = webView.settings
            webSettings.domStorageEnabled = true
            webSettings.javaScriptEnabled = true
        }
    }

    @BindingAdapter("configurationWithAdapter", "recyclerItems")
    @JvmStatic
    @Synchronized
    fun <T> configureRecyclerView(
        recyclerView: RecyclerView,
        configuration: RecyclerConfiguration?,
        items: List<T>?
    ) {
        val baseRecyclerAdapter = recyclerView.adapter as BaseRecyclerAdapter<*>?
        if (baseRecyclerAdapter != null) {
            setItems(recyclerView, items)
        } else {
            configureRecyclerView(recyclerView, configuration)
            setItems(recyclerView, items)
        }
    }

    @BindingAdapter("configuration")
    @JvmStatic
    @Synchronized
    fun configureRecyclerView(recyclerView: RecyclerView, configuration: RecyclerConfiguration?) {
        if (configuration == null) {
            return
        }
        val layoutManager = configuration.layoutManager
        val orientation = configuration.orientation

        if (recyclerView.layoutManager == null) {
            layoutManager?.let {
                recyclerView.layoutManager = it
            } ?: run {
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, orientation, false)
            }
        }

        recyclerView.itemAnimator = configuration.itemAnimator
        recyclerView.setHasFixedSize(configuration.isHasFixedSize)
        configuration.itemDecoration?.let {
            recyclerView.addItemDecoration(it)
        }

        configuration.adapter?.let {
            recyclerView.adapter = configuration.adapter
        }
    }

    @BindingAdapter("genericAdapter", "actionHandler", "recyclerItems")
    @JvmStatic
    fun <T> setAdapter(
        view: RecyclerView,
        layoutId: Int,
        viewModel: BaseViewModel<*>?,
        items: List<T>?
    ) {
        try {
            if (view.adapter == null) {
                val genericAdapter = GenericAdapter(layoutId)
                viewModel?.let { genericAdapter.setActionModel(it) }
                view.adapter = genericAdapter
            }
            setItems(view, items)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("genericAdapter")
    @JvmStatic
    fun setHandler(view: RecyclerView, layoutId: Int) {
        setAdapter<Any>(view, layoutId, null, null)
    }

    @BindingAdapter("actionHandler")
    @JvmStatic
    fun setAdapter(view: RecyclerView, baseViewModel: BaseViewModel<*>) {
        try {
            (view.adapter as BaseRecyclerAdapter<*>?)?.setActionModel(baseViewModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("recyclerItems")
    @JvmStatic
    fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {
        val itemList = ArrayList<T>()
        val baseRecyclerAdapter = recyclerView.adapter as BaseRecyclerAdapter<*>?
        baseRecyclerAdapter?.let { adapter ->
            if (items != null) {
                itemList.clear()
                itemList.addAll(items)
                adapter.updateList(itemList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    @BindingAdapter("imageUrl", "placeHolder")
    @JvmStatic
    fun ImageView.onImageSet(url: String?, placeholder: Drawable?) {
        val placeHolder = placeholder ?: ContextCompat.getDrawable(this.context, R.drawable.noimage)
        try {
            url?.let {
                Glide.with(this)
                    .load(url)
                    .apply(
                        RequestOptions()
                            .placeholder(placeHolder)
                            .centerCrop()
                    )
                    .error(placeHolder)
                    .into(this)
            }
        } catch (e: Exception) {
            Glide.with(this).load(placeHolder).into(this)
            e.printStackTrace()
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.onImageSet(url: String?) {
        val placeHolder = ContextCompat.getDrawable(this.context, R.drawable.noimage)
        try {
            url?.let {
                Glide.with(this)
                    .load(url)
                    .apply(
                        RequestOptions()
                            .placeholder(placeHolder)
                            .centerCrop()
                    )
                    .error(placeHolder)
                    .into(this)
            }
        } catch (e: Exception) {
            Glide.with(this).load(placeHolder).into(this)
            e.printStackTrace()
        }
    }

    @BindingAdapter("circularImageUrl", "placeHolder")
    @JvmStatic
    fun ImageView.onCircularImageSet(url: String?, placeholder: Drawable?) {
        val placeHolder = placeholder ?: ContextCompat.getDrawable(this.context, R.drawable.noimage)
        try {
            url?.let {
                Glide.with(this)
                    .load(url)
                    .apply(
                        RequestOptions()
                            .placeholder(placeHolder)
                            .circleCrop()
                    )
                    .error(placeHolder)
                    .into(this)
            }
        } catch (e: Exception) {
            Glide.with(this).load(placeHolder).apply(RequestOptions().circleCrop()).into(this)
            e.printStackTrace()
        }
    }

    @BindingAdapter("imageDrawableEnd")
    @JvmStatic
    fun TextView.setImageDrawableEnd(@DrawableRes imageDrawable: Int?) {
        imageDrawable?.let {
            val drawable = ContextCompat.getDrawable(context, imageDrawable)
            this.setCompoundDrawables(null, null, drawable, null)
        }
    }

    @BindingAdapter("imageDrawableStart")
    @JvmStatic
    fun TextView.setImageDrawableStart(@DrawableRes imageDrawable: Int?) {
        imageDrawable?.let {
            val drawable = ContextCompat.getDrawable(context, imageDrawable)
            this.setCompoundDrawables(drawable, null, null, null)
        }
    }

    fun ViewPager.setAdapter() {
        adapter
    }
}