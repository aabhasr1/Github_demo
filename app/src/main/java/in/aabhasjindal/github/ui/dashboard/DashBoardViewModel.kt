package `in`.aabhasjindal.github.ui.dashboard

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.data.repository.contract.UserRepository
import `in`.aabhasjindal.github.ui.base.BaseViewModel
import `in`.aabhasjindal.github.ui.base.GenericAdapter
import `in`.aabhasjindal.github.ui.base.RecyclerConfiguration
import android.view.View
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.core.inject

class DashBoardViewModel : BaseViewModel<DashBoardEvents>() {
    private val userRepository: UserRepository by inject()

    val repoList = ObservableField<List<Repo>>()
    val configuration: RecyclerConfiguration by lazy {
        RecyclerConfiguration().apply {
            adapter = GenericAdapter(R.layout.adapter_dashboard_repo, this@DashBoardViewModel)
            layoutManager = GridLayoutManager(event.parentContext, 2)
        }
    }

    fun onRepoClick(item: Repo) = View.OnClickListener {

    }

    fun fetchRepos(name: String) {
        launchIO {
            userRepository.fetchRepositories(name).execute({
                repoList.set(it)
            }, {
                event.onGeneralError(it)
            })
        }
    }
}