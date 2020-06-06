package `in`.aabhasjindal.github.di

import `in`.aabhasjindal.github.ui.dashboard.DashBoardViewModel
import `in`.aabhasjindal.github.ui.pullrequest.PRViewModel
import `in`.aabhasjindal.github.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashBoardViewModel() }
    viewModel { SearchViewModel() }
    viewModel { PRViewModel() }
}