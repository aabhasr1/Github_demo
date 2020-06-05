package `in`.aabhasjindal.github.ui.search

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.databinding.ActivitySearchBinding
import `in`.aabhasjindal.github.ui.base.BaseActivity
import `in`.aabhasjindal.github.utils.Constants
import `in`.aabhasjindal.github.utils.navigateToDashBoardActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import org.koin.core.inject

class SearchActivity : BaseActivity(), SearchEvents {
    private val model: SearchViewModel by inject()
    private val binding: ActivitySearchBinding by lazyBinding()

    override val layoutResource = R.layout.activity_search
    override fun getViewModel() = model

    private val preInput: String? by lazy { intent.extras?.getString(Constants.IntentConstants.SEARCH_TEXT) }

    override fun setBindings() {
        binding.model = model
        setSearchHandler()
    }

    private fun setSearchHandler() {
        preInput?.let {
            model.searchString.set(it)
            binding.imageView.setOnClickListener {
                onBackPressed()
                binding.imageView.visibility = View.VISIBLE
            }
        } ?: let {
            binding.imageView.visibility = View.INVISIBLE
        }
        model.setSearchHandler(binding.searchEntry, binding.searchAction)
    }

    override fun initUi(savedInstanceState: Bundle?) {
    }

    override fun onUserSelected(user: User) {
        preInput?.let {
            setResult(RESULT_OK, Intent().apply { putExtra(Constants.IntentConstants.USER, user) })
            finish()
        } ?: navigateToDashBoardActivity(user)
    }

    override fun setEventHandler() {
        model.setEventHandler(this)
    }
}