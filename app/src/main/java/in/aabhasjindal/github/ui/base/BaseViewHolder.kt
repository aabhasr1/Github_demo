package `in`.aabhasjindal.github.ui.base

import `in`.aabhasjindal.github.BR
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Any?,
        baseRecyclerAdapter: BaseRecyclerAdapter<T>,
        position: Int,
        baseViewModel: BaseViewModel<*>?
    ) {
        binding.apply {
            item?.let { setVariable(BR.item, it) }
            setVariable(BR.position, position)
            setVariable(BR.model, baseViewModel)
            executePendingBindings()
        }
    }
}