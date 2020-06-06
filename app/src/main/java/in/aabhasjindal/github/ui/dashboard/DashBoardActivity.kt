package `in`.aabhasjindal.github.ui.dashboard

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.binding.BindingUtil.onImageSet
import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.databinding.ActivityDashboardBinding
import `in`.aabhasjindal.github.ui.base.BaseActivity
import `in`.aabhasjindal.github.utils.Constants
import `in`.aabhasjindal.github.utils.navigateToPullRequests
import `in`.aabhasjindal.github.utils.navigateToSearchActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import org.koin.core.inject

class DashBoardActivity : BaseActivity(), DashBoardEvents {
    private val model: DashBoardViewModel by inject()
    private val binding: ActivityDashboardBinding by lazyBinding()

    override val layoutResource = R.layout.activity_dashboard
    override fun getViewModel() = model

    override val user: User? by lazy { intent.extras?.getParcelable<User>(Constants.IntentConstants.USER) }

    override fun setBindings() {
        binding.model = model
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_dashboard, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> onSearchSelected()
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initUi(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.materialToolbar)
        user?.let {
            setUser(it)
        }
    }

    private fun onSearchSelected() {
        user?.let {
            navigateToSearchActivity(it.name)
        } ?: navigateToSearchActivity(null)
    }

    override fun setEventHandler() {
        model.setEventHandler(this)
    }

    private fun setUser(user: User) {
        binding.profilePic.onImageSet(user.photo)
        binding.title.text = user.name
        model.fetchRepos(user.name)
    }

    override fun onRepoSelect(repo: Repo) {
        runOnUiThread {
            navigateToPullRequests(repo)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.IntentConstants.SEARCH_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                data?.extras?.getParcelable<User>(Constants.IntentConstants.USER)?.let {
                    setUser(it)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}