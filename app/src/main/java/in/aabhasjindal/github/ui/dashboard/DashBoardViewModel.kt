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
    val loading = ObservableField(false)
    val noRepos = ObservableField(false)

    fun onRepoClick(item: Repo) = View.OnClickListener {
        event.onRepoSelect(item)
    }

    fun fetchRepos(name: String) {
        loading.set(true)
        launchIO {
            userRepository.fetchRepositories(name).execute({
                loading.set(false)
                if (it.isNotEmpty()) {
                    repoList.set(it)
                    noRepos.set(false)
                } else {
                    noRepos.set(true)
                }
            }, {
                loading.set(false)
                event.onGeneralError(it)
            })
        }
    }
}