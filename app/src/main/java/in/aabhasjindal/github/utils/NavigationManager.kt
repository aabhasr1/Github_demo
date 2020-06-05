package `in`.aabhasjindal.github.utils

import `in`.aabhasjindal.github.data.model.User
import `in`.aabhasjindal.github.ui.dashboard.DashBoardActivity
import `in`.aabhasjindal.github.ui.search.SearchActivity
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle

fun Activity.navigateToDashBoardActivity(user: User) {
    launchActivity<DashBoardActivity> {
        putExtra(Constants.IntentConstants.USER, user)
    }
}

fun Activity.navigateToSearchActivity(text: String? = null) {
    launchActivity<SearchActivity>(Constants.IntentConstants.SEARCH_REQUEST) {
        text?.let { it ->
            putExtra(Constants.IntentConstants.SEARCH_TEXT, it)
        }
    }
}

inline fun <reified T : ContextWrapper> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {

    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : ContextWrapper> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {

    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : ContextWrapper> newIntent(context: Context): Intent =
    Intent(context, T::class.java)