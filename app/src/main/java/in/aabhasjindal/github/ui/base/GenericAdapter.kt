package `in`.aabhasjindal.github.ui.base

open class GenericAdapter : BaseRecyclerAdapter<Any> {
    private var items: List<Any> = emptyList()
    private var layoutId: Int = 0
    final override var baseViewModel: BaseViewModel<*>? = null
        private set

    override val data: List<Any>
        get() = this.items

    constructor(layoutId: Int) {
        this.layoutId = layoutId
    }

    constructor(layoutId: Int, baseViewModel: BaseViewModel<*>) {
        this.layoutId = layoutId
        this.baseViewModel = baseViewModel
    }

    @Suppress("UNCHECKED_CAST")
    override fun updateList(list: List<*>) {
        this.items = list as List<Any>
    }

    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) items.size else 0
    }

    override fun setActionModel(viewModel: BaseViewModel<*>) {
        this.baseViewModel = viewModel
    }

    override fun getObjForPosition(position: Int): Any? {
        return items[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun removeItemFromList(position: Int) {}
}