package `in`.aabhasjindal.github.ui.pullrequest

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.binding.BindingUtil.onImageSet
import `in`.aabhasjindal.github.data.model.PullRequest
import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.databinding.ActivityPrBinding
import `in`.aabhasjindal.github.ui.base.BaseActivity
import `in`.aabhasjindal.github.utils.Constants
import `in`.aabhasjindal.github.utils.openURL
import android.os.Bundle
import android.view.MenuItem
import org.koin.core.inject


class PRActivity : BaseActivity(), PREvents {
    private val model: PRViewModel by inject()
    private val binding: ActivityPrBinding by lazyBinding()

    override val layoutResource = R.layout.activity_pr
    override fun getViewModel() = model

    private val repo: Repo? by lazy { intent.extras?.getParcelable<Repo>(Constants.IntentConstants.REPO) }

    override fun setBindings() {
        binding.model = model
        repo?.let {
            binding.profilePic.onImageSet(it.user.photo)
            binding.title.text = it.name
        }
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.adapter = model.prAdapter
    }

    override fun initUi(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        repo?.let {
            model.fetchAllPr(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPRSelected(pullRequest: PullRequest) {
        runOnUiThread {
            openURL(pullRequest.webUrl)
        }
    }

    override fun setEventHandler() {
        model.setEventHandler(this)
    }
}