package `in`.aabhasjindal.github.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.*

object SearchTextHandler {
    fun handleSearchText(
        editText: EditText,
        viewModelScope: CoroutineScope,
        searchTextListener: SearchTextListener,
        debounceTime: Long = 300
    ) {
        var initcount = 0
        var firedOnce = false
        var searchText = ""
        var job: Job? = null
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.length?.let { length ->
                    if (length > 0) {
                        if (initcount == 0) {
                            searchTextListener.onSearchTextNotEmpty()
                        }
                        if (length > 2) {
                            job?.cancel()
                            job = viewModelScope.launch(Dispatchers.Main) {
                                if (firedOnce)
                                    delay(debounceTime)  //debounce timeOut
                                if (searchText != s.toString()) {
                                    searchTextListener.onSearchTextGoneInvalid()
                                    return@launch
                                }
                                firedOnce = true
                                searchTextListener.onSearchValidText(s.toString())
                            }
                            searchText = s.toString()
                        }
                    } else {
                        if (initcount != 0) {
                            searchTextListener.onSearchTextEmpty()
                        }
                    }
                    initcount = length
                }
            }
        })
    }
}

interface SearchTextListener {
    fun onSearchTextEmpty()
    fun onSearchTextNotEmpty()
    fun onSearchValidText(string: String)
    fun onSearchTextGoneInvalid()
}