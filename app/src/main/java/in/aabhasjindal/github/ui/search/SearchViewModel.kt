package `in`.aabhasjindal.github.ui.search

import `in`.aabhasjindal.github.R
import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.data.repository.contract.SearchRepository
import `in`.aabhasjindal.github.ui.base.BaseViewModel
import `in`.aabhasjindal.github.ui.base.GenericAdapter
import `in`.aabhasjindal.github.ui.base.RecyclerConfiguration
import `in`.aabhasjindal.github.ui.base.custom.SeparatorDecoration
import `in`.aabhasjindal.github.utils.SearchTextHandler
import `in`.aabhasjindal.github.utils.SearchTextListener
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.ObservableField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.inject

class SearchViewModel : BaseViewModel<SearchEvents>() {
    private val searchRepository: SearchRepository by inject()

    val searchString = ObservableField<String>()
    val searchList = ObservableField<List<User>>()
    val configuration: RecyclerConfiguration by lazy {
        RecyclerConfiguration().apply {
            adapter = GenericAdapter(R.layout.adapter_user_search, this@SearchViewModel)
            itemDecoration = SeparatorDecoration(event.parentContext, R.color.divider, 1f)
        }
    }

    fun setSearchHandler(searchText: AppCompatEditText, searchAction: ImageView) {
        val clickListener = View.OnClickListener {
            searchText.setText("")
            searchList.set(emptyList())
        }
        searchAction.setImageDrawable(null)
        var job: Job? = null
        SearchTextHandler.handleSearchText(searchText, CoroutineScope(Dispatchers.IO), object :
            SearchTextListener {
            override fun onSearchTextEmpty() {
                searchAction.setOnClickListener(null)
                searchAction.setImageDrawable(null)
                searchList.set(emptyList())
            }

            override fun onSearchTextNotEmpty() {
                searchAction.setOnClickListener(clickListener)
                searchAction.setImageDrawable(event.parentContext.getDrawable(R.drawable.ic_close_white_24dp))
            }

            override fun onSearchValidText(string: String) {
                job = fetchAndDisplaySuggestions(string)
            }

            override fun onSearchTextGoneInvalid() {
                job?.cancel()
                searchList.set(emptyList())
            }
        })
    }

    private fun fetchAndDisplaySuggestions(searchString: String): Job? {
        return launchIO {
            searchRepository.searchUser(searchString).execute({ list ->
                if (list.isNotEmpty()) {
                    searchList.set(list)
                } else {
                    searchList.set(
                        listOf(
                            User(
                                event.parentContext.getString(R.string.no_user),
                                0L,
                                "",
                                false
                            )
                        )
                    )
                }
            }, {
                event.onGeneralError(it)
            })
        }
    }

    fun onUserSelect(user: User) = View.OnClickListener {
        event.onUserSelected(user)
    }

}
