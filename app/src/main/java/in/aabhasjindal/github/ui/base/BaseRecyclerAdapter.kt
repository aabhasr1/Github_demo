package `in`.aabhasjindal.github.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    abstract val baseViewModel: BaseViewModel<*>?
    abstract val data: List<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        ) as ViewDataBinding
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val objForPosition = getObjForPosition(position)
        objForPosition?.let { obj ->
            holder.bind(obj, this, position, baseViewModel)
        } ?: run { baseViewModel?.let { holder.bind(null, this, position, it) } }
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    abstract fun setActionModel(viewModel: BaseViewModel<*>)
    protected abstract fun getObjForPosition(position: Int): Any?
    protected abstract fun getLayoutIdForPosition(position: Int): Int
    abstract fun updateList(list: List<*>)
    abstract fun removeItemFromList(position: Int)
}