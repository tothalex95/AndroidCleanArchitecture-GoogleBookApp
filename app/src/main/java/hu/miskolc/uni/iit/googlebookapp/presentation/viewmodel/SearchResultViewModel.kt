package hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks

class SearchResultViewModel(
    private val getBooks: GetBooks
) : BaseViewModel(getBooks) {

    val searchResultLiveData = MutableLiveData<SearchResult>()

    fun search(query: GetBooks.Params) {
        getBooks(
            onSuccess = { searchResultLiveData.value = it },
            onError = {},
            params = query
        )
    }

}