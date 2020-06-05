package `in`.aabhasjindal.github.ui.base

import `in`.aabhasjindal.github.BR
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerConfiguration() : BaseObservable() {

    var layoutManager: RecyclerView.LayoutManager? = null
        @Bindable
        set(layoutManager) {
            field = layoutManager
            notifyPropertyChanged(BR.layoutManager)
        }
    internal var itemAnimator: RecyclerView.ItemAnimator? = null
    var itemDecoration: RecyclerView.ItemDecoration? = null
        @Bindable
        set(itemDecoration) {
            field = itemDecoration
            notifyPropertyChanged(BR.itemDecoration)
        }
    internal var orientation: Int = 0
    var isHasFixedSize: Boolean = false

    @get:Bindable
    var adapter: RecyclerView.Adapter<*>? = null
        set(adapter) {
            field = adapter
            notifyPropertyChanged(BR.adapter)
        }

    constructor(orientation: Int, adapter: GenericAdapter) : this() {
        this.orientation = orientation
        this.adapter = adapter
    }

    init {
        itemAnimator = DefaultItemAnimator()
        orientation = LinearLayoutManager.VERTICAL
        isHasFixedSize = true
    }

    fun getOrientation(): Int {
        return orientation
    }

    @Bindable
    fun setOrientation(orientation: Int) {
        this.orientation = orientation
        notifyPropertyChanged(BR.orientation)
    }

    fun getItemAnimator(): RecyclerView.ItemAnimator? {
        return itemAnimator
    }

    @Bindable
    fun setItemAnimator(itemAnimator: RecyclerView.ItemAnimator) {
        this.itemAnimator = itemAnimator
        notifyPropertyChanged(BR.itemAnimator)
    }


}