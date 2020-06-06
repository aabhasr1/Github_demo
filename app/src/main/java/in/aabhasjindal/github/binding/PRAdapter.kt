package `in`.aabhasjindal.github.binding

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.data.model.PullRequest
import `in`.aabhasjindal.github.data.model.RecyclerPullRequestData
import `in`.aabhasjindal.github.databinding.AdapterPrListBinding
import `in`.aabhasjindal.github.ui.base.GenericAdapter
import `in`.aabhasjindal.github.ui.base.RecyclerConfiguration
import `in`.aabhasjindal.github.ui.pullrequest.PRViewModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.viewpager.widget.PagerAdapter


class PRAdapter(private val context: Context, private val viewModel: PRViewModel) : PagerAdapter() {

    private val openPRList = ObservableField<List<PullRequest>>(emptyList())
    private val closedPRList = ObservableField<List<PullRequest>>(emptyList())
    private var openLoading = ObservableField(true)
    private val closedLoading = ObservableField(true)
    private var openNoData = ObservableField(false)
    private val closedNoData = ObservableField(false)

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<AdapterPrListBinding>(
            inflater,
            R.layout.adapter_pr_list,
            collection,
            false
        )
        binding.item = getPair(position)
        binding.model = viewModel
        collection.addView(binding.root)
        return binding.root
    }

    fun updateOpenList(list: List<PullRequest>) {
        if (list.isNotEmpty()) {
            openPRList.set(list)
            openNoData.set(false)
        } else {
            openNoData.set(true)
        }
        openLoading.set(false)
    }

    fun updateClosedList(list: List<PullRequest>) {
        if (list.isNotEmpty()) {
            closedPRList.set(list)
            openNoData.set(false)
        } else {
            closedNoData.set(true)
        }
        closedLoading.set(false)
    }

    private fun getPair(position: Int): RecyclerPullRequestData {
        return if (position == 0) {
            RecyclerPullRequestData(RecyclerConfiguration().apply {
                adapter = GenericAdapter(R.layout.adapter_pr_detail, viewModel)
            }, openPRList, openLoading, openNoData)
        } else {
            RecyclerPullRequestData(RecyclerConfiguration().apply {
                adapter = GenericAdapter(R.layout.adapter_pr_detail, viewModel)
            }, closedPRList, closedLoading, closedNoData)
        }
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View?)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            context.getString(R.string.open)
        } else {
            context.getString(R.string.closed)
        }
    }
}