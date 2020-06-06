package `in`.aabhasjindal.github.ui.pullrequest

import `in`.aabhasjindal.github.binding.PRAdapter
import `in`.aabhasjindal.github.data.model.PR_STATUS
import `in`.aabhasjindal.github.data.model.PullRequest
import `in`.aabhasjindal.github.data.model.Repo
import `in`.aabhasjindal.github.data.repository.contract.UserRepository
import `in`.aabhasjindal.github.ui.base.BaseViewModel
import android.view.View
import org.koin.core.inject

class PRViewModel : BaseViewModel<PREvents>() {
    private val userRepository: UserRepository by inject()

    val prAdapter: PRAdapter by lazy {
        PRAdapter(event.parentContext, this)
    }

    fun fetchAllPr(repo: Repo) {
        launchIO {
            userRepository.fetchPR(repo.user.name, repo.name, PR_STATUS.OPEN).execute({
                prAdapter.updateOpenList(it)
            }, {
                it.printStackTrace()
                event.onGeneralError(it)
            })
        }
        launchIO {
            userRepository.fetchPR(repo.user.name, repo.name, PR_STATUS.CLOSED).execute({
                prAdapter.updateClosedList(it)
            }, {
                it.printStackTrace()
                event.onGeneralError(it)
            })
        }
    }

    fun onPRClick(item: PullRequest) = View.OnClickListener {
        event.onPRSelected(item)
    }
}