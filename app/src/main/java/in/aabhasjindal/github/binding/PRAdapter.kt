package `in`.aabhasjindal.github.binding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter


class PRAdapter(val context: Context) : PagerAdapter() {

    val pagerList = mutableListOf<>()

    override fun instantiateItem(collection: ViewGroup, position: Int): Any? {

        val inflater = LayoutInflater.from(mContext)
        val layout =
            inflater.inflate(modelObject.getLayoutResId(), collection, false) as ViewGroup
        collection.addView(layout)
        return layout
    }

    fun destroyItem(collection: ViewGroup, position: Int, view: Any?) {
        collection.removeView(view as View?)
    }

    override fun getCount(): Int {
        return ModelObject.values().length
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val customPagerEnum: ModelObject = ModelObject.values().get(position)
        return mContext.getString(customPagerEnum.getTitleResId())
    }
}