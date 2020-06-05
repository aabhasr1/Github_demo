package `in`.aabhasjindal.github.ui.base.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val space: Int,
    private val startSpacing: Boolean = false,
    private val endSpacing: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = if (parent.getChildAdapterPosition(view) == 0) 0 else space
            if (startSpacing) left = space
            if (endSpacing) right = space
            bottom = space
        }
    }
}